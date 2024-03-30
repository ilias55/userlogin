package com.userlogin.service;

import com.userlogin.entity.User;
import com.userlogin.model.*;

public interface UserService {
    boolean saveUser(User user);

    LoginSuccessModel loginUser(UserAuthModel userAuthModel);
}
