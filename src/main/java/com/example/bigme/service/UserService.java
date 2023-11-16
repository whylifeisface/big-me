package com.example.bigme.service;

import com.example.bigme.pojo.User;
import com.example.bigme.service.imp.UserServiceImp;

public interface UserService  {

    User findUserByName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatar);

    void updatePwd(String new_pwd);

}
