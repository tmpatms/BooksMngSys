package org.ddd.service.impl;

import org.ddd.constant.ResponseConstant;
import org.ddd.constant.UserConstant;
import org.ddd.entity.User;
import org.ddd.mapper.UserMapper;
import org.ddd.protocol.*;
import org.ddd.protocol.user.*;
import org.ddd.service.UserServiceEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Service
public class UserServiceImpl implements UserServiceEngine {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
    }

    @Override
    public  Response login(LoginUser userInfo) {
        Response response = new Response();

        User user = this.getUserIfExists(userInfo.getId());
        if (user == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg(ResponseConstant.USER_NOT_FOUND);
        } else {
            if (!Objects.equals(user.getPassword(), userInfo.getPassword())) {
                response.setStatus(ResponseConstant.FAIL);
                response.setMsg(ResponseConstant.PASSWORD_NOT_MATCHED);
            } else {
                response.setStatus(ResponseConstant.SUCCESS);
                response.setMsg(user.getIdentity());
            }
        }

        return response;
    }

    @Override
    public Response registerUser(RegisterUser userInfo) {
        Response response = new Response();

        User user = new User();
        Long id = UUID.randomUUID().getMostSignificantBits();
        user.setId(id);
        user.setUserName(userInfo.getUserName());
        user.setPassword(userInfo.getPassword());
        user.setTelephone(userInfo.getTelephone());
        user.setIdentity(UserConstant.DEFAULT_USER_IDENTITY);

        int insertedNum = userMapper.insert(user);

        if (insertedNum >= 1) {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(id.toString());
        } else {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Insert user fail.");
        }

        return response;
    }

    @Override
    public Response updateUserInfo(UpdateUser userInfo) {
        Response response = new Response();

        User user = this.getUserIfExists(userInfo.getId());
        if (user == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg(ResponseConstant.USER_NOT_FOUND);

            return response;
        }

        user.setUserName(userInfo.getUserName());
        user.setPassword(userInfo.getPassword());
        user.setTelephone(userInfo.getTelephone());

        return updateUserInfo(response, user);
    }

    @Override
    public Response getUserList() {
        Response response = new Response();

        List<User> userList = userMapper.selectList(null);

        response.setStatus(ResponseConstant.SUCCESS);
        response.setMsg(userList.toString());

        return response;
    }

    //Only admin can invoke this.
    @Override
    public Response deleteUser(DeleteUser userInfo) {
        Response response = new Response();

        int delCount = userMapper.deleteById(userInfo.getId());
        if (delCount >= 1) {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg("Delete user success.");
        } else {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Delete user fail.");
        }

        return response;
    }

    //Only admin can invoke this.
    @Override
    public Response setIdentity(SetIdentity userInfo) {
        Response response = new Response();

        User user = getUserIfExists(userInfo.getId());
        user.setIdentity(userInfo.getIdentity());
        return updateUserInfo(response, user);
    }

    private Response updateUserInfo(Response response, User user) {
        int updateCount = userMapper.updateById(user);
        if (updateCount >= 1) {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg("Update user success.");
        } else {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Update user fail.");
        }

        return response;
    }

    private User getUserIfExists(Long id) {
        return userMapper.selectById(id);
    }
}
