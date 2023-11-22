package com.example.bigme.service.imp;

import com.example.bigme.mapper.UserMapper;
import com.example.bigme.pojo.User;
import com.example.bigme.service.UserService;
import com.example.bigme.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;


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
        String s = new String(DigestUtils.md5DigestAsHex(password.getBytes()));

        userMapper.register(username, s);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updateAvatar(avatar, id);
    }

    @Override
    public void updatePwd(String new_pwd) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updatePwd(DigestUtils.md5DigestAsHex(new_pwd.getBytes()), id);
    }
}
