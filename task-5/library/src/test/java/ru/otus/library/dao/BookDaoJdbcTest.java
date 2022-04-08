package ru.otus.library.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {
    private static final int EXISTING_BOOK_ID = 1;
    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book(2, "The Hobbit", new Author(1, "John Ronald Reuel Tolkien"),
                new Genre(1, "Fantasy"), 1937, 310);
        bookDaoJdbc.insert(expectedBook);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, "The Lord of the Rings",
                new Author(1, "John Ronald Reuel Tolkien"),
                new Genre(1, "Fantasy"), 1955, 2000);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void shouldCorrectUpdateBook() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, "The Lord of the Rings",
                new Author(1, "John Ronald Reuel Tolkien"),
                new Genre(1, "Fantasy"), 1955, 2500);

        bookDaoJdbc.update(expectedBook);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnExpectedBookList() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, "The Lord of the Rings",
                new Author(1, "John Ronald Reuel Tolkien"),
                new Genre(1, "Fantasy"), 1955, 2000);
        List<Book> actualBookList = bookDaoJdbc.getAll();
        assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

}
