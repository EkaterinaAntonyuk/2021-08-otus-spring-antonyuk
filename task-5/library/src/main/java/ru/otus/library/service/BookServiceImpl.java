package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.BookDao;
import ru.otus.library.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void insertBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }
}
