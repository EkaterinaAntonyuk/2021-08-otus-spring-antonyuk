package ru.otus.library.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void shouldReturnExpectedGenreList() {
        Genre expectedGenre = new Genre(1, "Fantasy");
        List<Genre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre);
    }
}
