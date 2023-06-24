package org.ddd.controller;

import org.ddd.protocol.Response;
import org.ddd.protocol.book.*;
import org.ddd.service.BookServiceEngine;
import org.ddd.service.UserServiceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dudaidong
 * @date 2023/06/24
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceEngine bookServiceEngine;

    @GetMapping("/getbooklist")
    public Response getBookList() {
        return bookServiceEngine.getBookList();
    }

    @PostMapping("/addbook")
    public Response addBook(@RequestParam("name") String name,
                            @RequestParam("position") String position) {
        AddBook addBook = new AddBook();
        addBook.setName(name);
        addBook.setPosition(position);
        return bookServiceEngine.addBook(addBook);
    }

    @PostMapping("/removebook")
    public Response removeBook(@RequestParam("id") Long id) {
        RemoveBook removeBook = new RemoveBook();
        removeBook.setId(id);
        return bookServiceEngine.removeBook(removeBook);
    }

    @PostMapping("/searchbookbyid")
    public Response searchBookById(@RequestParam("id") Long id) {
        SearchBook searchBook = new SearchBook();
        searchBook.setId(id);
        return bookServiceEngine.searchBookById(searchBook);
    }

    @PostMapping("/searchbookbyname")
    public Response searchBookByName(@RequestParam("name") String name) {
        SearchBook searchBook = new SearchBook();
        searchBook.setName(name);
        return bookServiceEngine.searchBookByName(searchBook);
    }

//    @PostMapping("/searchbookbyauthor")
//    public Response searchBookByAuthor(@RequestParam("author") String author) {
//        SearchBook searchBook = new SearchBook();
//        searchBook.setName(name);
//        return bookServiceEngine.searchBookByName(searchBook);
//    }

    @PostMapping("/borrowbook")
    public Response borrowBook(@RequestParam("id") Long id) {
        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setId(id);
        return bookServiceEngine.borrowBook(borrowBook);
    }

    @PostMapping("/returnbook")
    public Response returnBook(@RequestParam("id") Long id) {
        ReturnBook returnBook = new ReturnBook();
        returnBook.setId(id);
        return bookServiceEngine.returnBook(returnBook);
    }

    @PostMapping("/getbookinfo")
    public Response getBookInfo(@RequestParam("id") Long id) {
        GetBookInfo getBookInfo = new GetBookInfo();
        getBookInfo.setId(id);
        return bookServiceEngine.getBookInfo(getBookInfo);
    }
}
