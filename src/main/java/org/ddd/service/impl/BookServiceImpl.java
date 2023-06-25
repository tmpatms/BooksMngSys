package org.ddd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ddd.constant.BookConstant;
import org.ddd.constant.ResponseConstant;
import org.ddd.entity.Book;
import org.ddd.mapper.BookMapper;
import org.ddd.protocol.Response;
import org.ddd.protocol.book.*;
import org.ddd.service.BookServiceEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author dudaidong
 * @date 2023/06/23
 */
@Service
public class BookServiceImpl implements BookServiceEngine {

    private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Response addBook(AddBook bookInfo) {
        Response response = new Response();
        Book book = new Book();

        book.setName(bookInfo.getName());
        book.setPosition(bookInfo.getPosition());
        book.setStatus(0);
        book.setBorrowedCount(0);

        int insertCount = bookMapper.insert(book);
        if (insertCount >= 1) {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg("Add book to library success.");
        } else {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Add book to library fail.");
        }

        return response;
    }

    @Override
    public Response removeBook(RemoveBook bookInfo) {
        Response response = new Response();

        int delCount = bookMapper.deleteById(bookInfo.getId());
        if (delCount >= 1) {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg("Delete book to library success.");
        } else {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Delete book to library fail.");
        }

        return response;
    }

    @Override
    public Response searchBookById(SearchBook bookInfo) {
        Response response = new Response();

        Book book = bookMapper.selectById(bookInfo.getId());
        if (book == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Book info not found by given id.");
        } else {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(book.toString());
        }

        return response;
    }

    @Override
    public Response searchBookByName(SearchBook bookInfo) {
        Response response = new Response();

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", bookInfo.getName());

        Book book = bookMapper.selectOne(queryWrapper);
        if (book == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Book info not found by given id.");
        } else {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(book.toString());
        }

        return response;
    }

    //undefined author in databases. ignore..
    @Override
    public Response searchBookByAuthor(SearchBook bookInfo) {
        return null;
    }

    @Override
    public Response borrowBook(BorrowBook bookInfo) {
        Response response = new Response();

        Book book = bookMapper.selectById(bookInfo.getId());
        if (book == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Book not found by given id.");
        } else if (Objects.equals(book.getStatus(), BookConstant.STATUS_BORROWWED)) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Book had been borrowed and not returned yet.");
        } else {
            book.setStatus(BookConstant.STATUS_BORROWWED);
            book.setBorrowedCount(book.getBorrowedCount() + 1);
            if (this.updateBookInfo(book)) {
                response.setStatus(ResponseConstant.SUCCESS);
                response.setMsg("Book has been borrowed success.");
            } else {
                response.setStatus(ResponseConstant.FAIL);
                response.setMsg("Update book info fail, please try again.");
            }
        }

        return response;
    }

    public boolean updateBookInfo(Book bookInfo) {
        if (bookMapper.updateById(bookInfo) >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public Response returnBook(ReturnBook bookInfo) {
        Response response = new Response();

        Book book = bookMapper.selectById(bookInfo.getId());
        if (book == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Book not found by given id.");
        } else {
            book.setStatus(BookConstant.STATUS_INLIBRARY);
            if (this.updateBookInfo(book)) {
                response.setStatus(ResponseConstant.SUCCESS);
                response.setMsg("Book has been returned success.");
            } else {
                response.setStatus(ResponseConstant.FAIL);
                response.setMsg("Update book info fail, please try again.");
            }
        }

        return response;
    }

    @Override
    public Response getBookInfo(GetBookInfo bookInfo) {
        Response response = new Response();

        Book book = bookMapper.selectById(bookInfo.getId());
        if (book == null) {
            response.setStatus(ResponseConstant.FAIL);
            response.setMsg("Book info not found.");
        } else {
            response.setStatus(ResponseConstant.SUCCESS);
            response.setMsg(book.toString());
        }

        return response;
    }

    @Override
    public Response getBookList() {
        Response response = new Response();

        List<Book> bookList = bookMapper.selectList(null);
        response.setStatus(ResponseConstant.SUCCESS);
        response.setMsg(bookList.toString());

        return response;
    }
}
