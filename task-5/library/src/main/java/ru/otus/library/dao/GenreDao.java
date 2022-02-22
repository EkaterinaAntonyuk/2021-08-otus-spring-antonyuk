package ru.otus.library.dao;

import ru.otus.library.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
}
