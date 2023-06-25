package org.ddd.controller;

import org.ddd.constant.ResponseConstant;
import org.ddd.constant.UserConstant;
import org.ddd.entity.User;
import org.ddd.interceptor.Interceptor;
import org.ddd.protocol.Response;
import org.ddd.protocol.user.*;
import org.ddd.service.UserServiceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author dudaidong
 * @date 2023/06/24
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceEngine userServiceEngine;

    @GetMapping("/getuserlist")
    public Response getUserList(HttpServletRequest request) {
        if (!this.checkAdmin(request)) {
            Response response = new Response<>();
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("No priority.");

            return response;
        }
        return userServiceEngine.getUserList();
    }

    @GetMapping("/setidentity")
    public Response setIdentity(@RequestParam("id") Long id,
                                @RequestParam("identity") String identity,
                                HttpServletRequest request) {
        if (!this.checkAdmin(request)) {
            Response response = new Response<>();
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("No priority.");

            return response;
        }
        SetIdentity setIdentity = new SetIdentity();
        setIdentity.setId(id);
        setIdentity.setIdentity(identity);
        return userServiceEngine.setIdentity(setIdentity);
    }

    @GetMapping("/deleteuser")
    public Response deleteUser(@RequestParam("id") Long id,
                               HttpServletRequest request) {
        if (!this.checkAdmin(request)) {
            Response response = new Response<>();
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("No priority.");

            return response;
        }
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.setId(id);
        return userServiceEngine.deleteUser(deleteUser);
    }

    private boolean checkAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        System.out.println("check admin user: " + user.toString());

        return ((User) user).getIdentity().equals("admin");
    }

    @PostMapping("/login")
    public Response login(@RequestParam("id") Long id,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
        Response response = new Response();
        LoginUser loginUser = new LoginUser();
        loginUser.setId(id);
        loginUser.setPassword(password);
        Response loginResponse = userServiceEngine.login(loginUser);
        HttpSession session = request.getSession();

        if (loginResponse.getStatus().equals(ResponseConstant.FAIL)) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Login fail, session not cached.");
        } else {
            User user = new User();
            user.setId(id);
            user.setIdentity(loginResponse.getMsg());
            session.setAttribute("user", user);

            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(user.toString());
        }
        return response;
    }

    @PostMapping("/updateuserinfo")
    public Response updateUserInfo(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("telephone") String telephone,
                                   HttpServletRequest request) {
        Response response = new Response();
        UpdateUser updateUser = new UpdateUser();
        updateUser.setUserName(username);
        updateUser.setPassword(password);
        updateUser.setTelephone(telephone);

        //auth
//        this.updateAuthValidate(updateUser, request);

        Response updateResponse = userServiceEngine.updateUserInfo(updateUser);

        if (updateResponse.getStatus().equals(ResponseConstant.FAIL)) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Update user info fail.");
        } else {
            User user = new User();
            user.setId(Long.valueOf(updateResponse.getMsg()));
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(user.toString());
        }
        return response;
    }

//    private Boolean updateAuthValidate(UpdateUser updateUser, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Object userObj = session.getAttribute("user");
//
//        User user = (User) userObj;
//
//    }

    @PostMapping("/registeruser")
    public Response registerUser(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("telephone") String telephone,
                                 HttpServletRequest request) {
        Response response = new Response();
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUserName(username);
        registerUser.setPassword(password);
        registerUser.setTelephone(telephone);
        Response registerResponse = userServiceEngine.registerUser(registerUser);

        System.out.println(registerResponse);

        if (registerResponse.getStatus().equals(ResponseConstant.FAIL)) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Register fail, session not cached.");
        } else {
            User user = new User();
            user.setId(Long.valueOf(registerResponse.getMsg()));
            user.setIdentity(UserConstant.DEFAULT_USER_IDENTITY);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(user.toString());
        }
        return response;
    }
}
