package org.ddd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dudaidong
 * @date 2023/06/23
 */

@Data
public class User {
    /**
     * primary key
     */
    private Long id;

    /**
     * username
     */
    private String userName;

    /**
     * password
     */
    private String password;

    /**
     * identity
     */
    private String identity;

    /**
     * telephone
     */
    private String telephone;
}
