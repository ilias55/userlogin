package com.userlogin.controller;

import com.userlogin.mapper.UserMapper;
import com.userlogin.model.LoginSuccessModel;
import com.userlogin.model.ResponseModel;
import com.userlogin.model.UserAuthModel;
import com.userlogin.model.UserModel;
import com.userlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;
    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserModel userModel){
       return ResponseEntity.ok(userService.saveUser(userMapper.entityFromModel(userModel)));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseModel> registerUser(@RequestBody UserAuthModel userAuthModel){
            System.out.println("UserLogin");

            ResponseModel loginSuccessModel = userService.loginUser(userAuthModel);


       return ResponseEntity.ok(loginSuccessModel);
    }
}
