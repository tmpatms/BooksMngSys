package org.ddd.service;

import org.ddd.protocol.user.LoginUser;
import org.ddd.protocol.user.RegisterUser;
import org.ddd.protocol.Response;
import org.ddd.protocol.user.UpdateUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceEngine userServiceEngine;

    @Test
    public void TestLogin() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(1L);
        loginUser.setPassword("test123123");

        Response response = userServiceEngine.login(loginUser);
        System.out.println(response);
    }

    @Test
    public void TestRegisterUser() {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUserName("test2");
        registerUser.setPassword("test2");
        registerUser.setTelephone("test2");
        Response response = userServiceEngine.registerUser(registerUser);
        System.out.println(response);
    }

    @Test
    public void TestUpdateUser() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setId(1L);
        updateUser.setUserName("daidong");
        updateUser.setPassword("test123123");
        updateUser.setTelephone("test123123");
        Response response = userServiceEngine.updateUserInfo(updateUser);
        System.out.println(response);
    }

    @Test
    public void TestGetUserList() {
        System.out.println(userServiceEngine.getUserList());
    }
}
