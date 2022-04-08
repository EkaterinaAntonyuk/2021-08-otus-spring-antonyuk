package ru.otus.library.dao;

import ru.otus.library.model.Book;

import java.util.List;

public interface BookDao {
    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    void update(Book book);
}
