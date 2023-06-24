package org.ddd.protocol;

import lombok.Data;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Data
public class RegisterUser {

    private String userName;

    private String password;

    private String telephone;

}
