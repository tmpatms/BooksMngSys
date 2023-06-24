package org.ddd.service;

import org.ddd.protocol.Response;
import org.ddd.protocol.book.*;
import org.ddd.protocol.user.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dudaidong
 * @date 2023/06/24
 */
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookServiceEngine bookServiceEngine;

    @Test
    public void TestListBooks() {
        System.out.println(bookServiceEngine.getBookList());
    }

    @Test
    public void TestGetBookInfo() {
        GetBookInfo bookInfo = new GetBookInfo();
        bookInfo.setId(1L);

        System.out.println(bookServiceEngine.getBookInfo(bookInfo));
    }

    @Test
    public void TestAddBook() {
        AddBook addBook = new AddBook();
        addBook.setName("test1");
        addBook.setPosition("10-1-0190");

        System.out.println(bookServiceEngine.addBook(addBook));
    }

    @Test
    public void TestSearchBookByName() {
        SearchBook searchBook = new SearchBook();
        searchBook.setName("the Shawshank Redemption");

        System.out.println(bookServiceEngine.searchBookByName(searchBook));
    }

    @Test
    public void TestBorrowBook() {
        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setId(1L);

        System.out.println(bookServiceEngine.borrowBook(borrowBook));
    }

    @Test
    public void TestReturnBook() {
        ReturnBook returnBook = new ReturnBook();
        returnBook.setId(1L);

        System.out.println(bookServiceEngine.returnBook(returnBook));
    }
}
