package ru.otus.library.dao;

import ru.otus.library.model.Author;

import java.util.List;

public interface AuthorDao{

    List<Author> getAll();
}
