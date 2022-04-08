package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Genre {
    private long id;
    private String name;

    public Genre(long id) {
        this.id = id;
    }
}
