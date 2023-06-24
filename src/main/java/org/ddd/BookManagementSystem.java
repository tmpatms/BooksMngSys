package org.ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("org.ddd.mapper")
@SpringBootApplication
public class BookManagementSystem {
    public static void main(String[] args) {
        SpringApplication.run(BookManagementSystem.class, args);
    }
}
