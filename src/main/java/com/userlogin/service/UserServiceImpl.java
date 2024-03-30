package com.userlogin.service;

import com.userlogin.config.JwTokenGenerator;
import com.userlogin.config.SecurityConfig;
import com.userlogin.entity.User;
import com.userlogin.exception.AuthCustomException;
import com.userlogin.model.*;
import com.userlogin.repository.UserRepository;
import com.userlogin.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
 public static final String LOGIN_SUCCESS = "Giriş Başarılı";
 public static final String LOGIN_ERROR = "Kullanıcı adı veya parola hatalı";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    JwTokenGenerator jwTokenGenerator;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public boolean saveUser(User userModel) {
        List<User> users = userRepository.findAll(UserSpecification.byUserNameOrEmail(userModel.getUserName(), userModel.getEmail()));
        if(users.isEmpty()) {
            userModel.setPassword(securityConfig.passwordEncoder().encode(userModel.getPassword()));
            User user = userRepository.save(userModel);
        } else {
            throw new AuthCustomException(false, "Kullancı adı daha önce alınmış!!", HttpStatus.BAD_REQUEST.value());
        }
        return true;

    }

    @Override
    public LoginSuccessModel loginUser(UserAuthModel userAuthModel) {
        User user = userRepository.findByUserName(userAuthModel.getUserName());
        LoginSuccessModel loginSuccessModel = new LoginSuccessModel();

        if(user != null) {
            Boolean isPwdRight = securityConfig.passwordEncoder().matches(userAuthModel.getPassword(), user.getPassword());
            if(isPwdRight) {

                loginSuccessModel.setSuccess(true);
                loginSuccessModel.setMessage(LOGIN_SUCCESS);
                loginSuccessModel.setHttpStatusCode(HttpStatus.OK.value());
                loginSuccessModel.setUser(new LoginUserResponseModel(user.getUserName(), user.getEmail(),jwTokenGenerator.generateToken(userAuthModel)));

            } else {
                throw new AuthCustomException(false, LOGIN_ERROR, HttpStatus.UNAUTHORIZED.value());
            }
        } else {
            throw new AuthCustomException(false, LOGIN_ERROR, HttpStatus.UNAUTHORIZED.value());
        }
        return loginSuccessModel;
    }
}
