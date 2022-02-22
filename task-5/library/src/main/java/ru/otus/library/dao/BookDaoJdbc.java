package ru.otus.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao{

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into books (id, name, author_id, genre_id, year, volume) " +
                        "values (:id, :name, :author_id, :genre_id, :year, :volume)",
                Map.of("id", book.getId(), "name", book.getName(), "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId(), "year", book.getYear(),
                        "volume", book.getVolume()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.name, b.author_id," +
                        "a.name as author_name," +
                        "b.genre_id," +
                        "g.name as genre_name, " +
                        "b.year, b.volume from books as b " +
                        "join authors as a " +
                        "on b.author_id = a.id " +
                        "join genres as g " +
                        "on b.genre_id = g.id " +
                        "where b.id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.id, b.name, b.author_id, a.name as author_name," +
                        "b.genre_id, g.name as genre_name, b.year, b.volume from books as b " +
                        "join authors as a " +
                        "on b.author_id = a.id " +
                        "join genres as g " +
                        "on b.genre_id = g.id ", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    @Override
    public void update(Book book) {

        namedParameterJdbcOperations.update("update books set name = :name, author_id = :author_id," +
                        " genre_id = :genre_id, year = :year, volume = :volume where id =:id",
                Map.of("id", book.getId(), "name", book.getName(), "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId(), "year", book.getYear(),
                        "volume", book.getVolume()));
    }


    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            String authorName = resultSet.getString("author_name");
            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");
            int year = resultSet.getInt("year");
            int volume = resultSet.getInt("volume");
            return new Book(id, name, new Author(authorId, authorName), new Genre(genreId, genreName), year, volume);
        }
    }
}
