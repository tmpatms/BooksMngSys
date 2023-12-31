package org.ddd.service;

import org.ddd.protocol.*;
import org.ddd.protocol.user.*;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
public interface UserServiceEngine {

    public Response login(LoginUser userInfo);

    public Response registerUser(RegisterUser userInfo);

    public Response updateUserInfo(UpdateUser userInfo);

    public Response getUserList();

    public Response deleteUser(DeleteUser userInfo);

    public Response setIdentity(SetIdentity userInfo);

}
