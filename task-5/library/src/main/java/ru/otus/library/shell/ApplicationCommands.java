package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ShellComponent
public class ApplicationCommands {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public ApplicationCommands(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "Get book by id", key = {"gb", "get"})
    public void getBookById() {
        System.out.println("Input book id: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long id = 0;
        try {
            id = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bookService.getBookById(id));
    }

    @ShellMethod(value = "Get all books", key = {"ab", "books"})
    public void getAllBooks() {
        System.out.println(bookService.getAllBooks());
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete"})
    public void deleteBookById() {
        System.out.println("Input book id: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long id = 0;
        try {
            id = Long.parseLong(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Insert new book", key = {"ib", "insert"})
    public void insertBook() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Book book = inputBookInformation(reader);
            bookService.insertBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Update book", key = {"ub", "update"})
    public void updateBook() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Book book = inputBookInformation(reader);
            bookService.updateBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "Get all authors", key = {"aa", "authors"})
    public void getAllAuthors() {
        System.out.println(authorService.getAllAuthors());
    }

    @ShellMethod(value = "Get all genres", key = {"ag", "genres"})
    public void getAllGenres() {
        System.out.println(genreService.getAllGenres());
    }

    private Book inputBookInformation(BufferedReader reader) throws IOException {
        System.out.println("Input book id: ");
        long id = Long.parseLong(reader.readLine());
        System.out.println("Input book name: ");
        String name = reader.readLine();
        System.out.println("Input author id: ");
        long authorId = Long.parseLong(reader.readLine());
        System.out.println("Input genre id: ");
        long genreId = Long.parseLong(reader.readLine());
        System.out.println("Input year: ");
        int year = Integer.parseInt(reader.readLine());
        System.out.println("Input volume: ");
        int volume = (int) Long.parseLong(reader.readLine());
        Author author = new Author(authorId);
        Genre genre = new Genre(genreId);
        return new Book(id, name, author, genre, year, volume);
    }
}
