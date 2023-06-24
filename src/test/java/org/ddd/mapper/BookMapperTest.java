package org.ddd.mapper;

import org.ddd.entity.Book;
import org.ddd.entity.User;
import org.ddd.mapper.BookMapper;
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
public class BookMapperTest {

    @Autowired(required = false)
    private BookMapper bookMapper;

    @Test
    public void TestQueryList() {
        List<Book> bookList = bookMapper.selectList(null);
        bookList.forEach(System.out::println);
    }
}
