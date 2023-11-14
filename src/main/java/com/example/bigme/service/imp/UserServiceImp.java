package com.example.bigme.service.imp;

import com.example.bigme.mapper.UserMapper;
import com.example.bigme.pojo.User;
import com.example.bigme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        User u = userMapper.findUserByName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {

        /*给密码加密*/
        String s = new String(DigestUtils.md5Digest(password.getBytes()));

        userMapper.register(username, s);
    }
}
