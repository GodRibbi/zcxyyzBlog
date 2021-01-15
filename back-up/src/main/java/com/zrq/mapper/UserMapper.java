package com.zrq.mapper;

import java.util.List;

import com.zrq.controller.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from users")
    public List<User> FindAll();
    @Insert("insert into users value(#{user.useremail},#{user.password})")
    public void AddUser(@Param("user") User user);
    @Select("select * from users where useremail=#{useremail}")
    public List<User> FindByEmail(@Param("useremail") String useremail);
    @Select("select * from users where useremail=#{useremail} and password=#{password}")
    public List<User> CheckUser(@Param("useremail") String useremail,@Param("password") String password);
    @Update("update users set password=#{password} where useremail=#{useremail}")
    public void UpdatePassword(@Param("useremail") String useremail,@Param("password") String password);
}
