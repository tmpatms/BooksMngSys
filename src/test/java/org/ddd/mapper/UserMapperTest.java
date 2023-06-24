package org.ddd.mapper;

import org.ddd.entity.User;
import org.ddd.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void TestQueryList() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
