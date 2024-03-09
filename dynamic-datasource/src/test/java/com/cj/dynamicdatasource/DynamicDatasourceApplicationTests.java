package com.cj.dynamicdatasource;

import com.cj.dynamicdatasource.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDatasourceApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {

        userService.getAllUsers();
    }

}
