package com.cj.dynamicdatasource.service;

import com.cj.dynamicdatasource.annotation.DataSource;
import com.cj.dynamicdatasource.mapper.UserMapper;
import com.cj.dynamicdatasource.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource("slave")
public class UserService {

    @Autowired
    UserMapper userMapper;


    public void getAllUsers(){
        List<User> allUsers = userMapper.getAllUsers();
        System.out.println("allUsers = " + allUsers);
    }
}
