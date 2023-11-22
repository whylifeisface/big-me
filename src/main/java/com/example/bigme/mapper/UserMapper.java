package com.example.bigme.mapper;

import com.example.bigme.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {


    //根据用户名查询用户

    @Select("select * from user where username = #{username}")
    User findUserByName(String username);


    //注册用户
    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password}, now(), now())")
    void register(String username, String password);


    @Update("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatar}, update_time = now() where id = #{id}")
    void updateAvatar(String avatar, Integer id);

    @Update("update user set password=#{pwd}, update_time=now(), where id=#{id}")
    void updatePwd(String pwd, Integer id);
}
