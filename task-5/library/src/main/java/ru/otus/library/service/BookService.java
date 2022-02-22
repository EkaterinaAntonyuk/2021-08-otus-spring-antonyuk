package ru.otus.library.service;

import ru.otus.library.model.Book;

import java.util.List;

public interface BookService {

    void insertBook(Book book);

    Book getBookById(long id);

    List<Book> getAllBooks();

    void deleteBookById(long id);

    void updateBook(Book book);
}
