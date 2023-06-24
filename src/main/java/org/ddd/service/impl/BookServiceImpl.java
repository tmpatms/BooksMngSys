package org.ddd.service.impl;

import org.ddd.protocol.Response;
import org.ddd.protocol.book.*;
import org.ddd.service.BookServiceEngine;
import org.springframework.stereotype.Service;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Service
public class BookServiceImpl implements BookServiceEngine {
    @Override
    public Response addBook(AddBook bookInfo) {
        return null;
    }

    @Override
    public Response removeBook(RemoveBook bookInfo) {
        return null;
    }

    @Override
    public Response searchBookById(SearchBook bookInfo) {
        return null;
    }

    @Override
    public Response searchBookByName(SearchBook bookInfo) {
        return null;
    }

    @Override
    public Response searchBookByAuthor(SearchBook bookInfo) {
        return null;
    }

    @Override
    public Response borrowBook(BorrowBook bookInfo) {
        return null;
    }

    @Override
    public Response returnBook(ReturnBook bookInfo) {
        return null;
    }

    @Override
    public Response getBookInfo(GetBookInfo bookInfo) {
        return null;
    }

    @Override
    public Response getBookList() {
        return null;
    }
}
