package org.ddd.service;

import org.ddd.protocol.Response;
import org.ddd.protocol.book.*;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
public interface BookServiceEngine {

    public Response addBook(AddBook bookInfo);

    public Response removeBook(RemoveBook bookInfo);

    public Response searchBookById(SearchBook bookInfo);

    public Response searchBookByName(SearchBook bookInfo);

    public Response searchBookByAuthor(SearchBook bookInfo);

    public Response borrowBook(BorrowBook bookInfo);

    public Response returnBook(ReturnBook bookInfo);

    public Response getBookInfo(GetBookInfo bookInfo);

    public Response getBookList();
}
