package ru.otus.library.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest {
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void shouldReturnExpectedAuthorList() {
        Author expectedAuthor = new Author(1, "John Ronald Reuel Tolkien");
        List<Author> actualAuthorList = authorDaoJdbc.getAll();
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor);
    }
}
