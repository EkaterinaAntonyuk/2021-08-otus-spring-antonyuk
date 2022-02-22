insert into authors (id, name) values (1, 'John Ronald Reuel Tolkien');
insert into authors (id, name) values (2, 'Jane Austen');
insert into authors (id, name) values (3, 'Philip Pullman');
insert into authors (id, name) values (4, 'Douglas Adams');

insert into genres (id, name) values (1, 'Fantasy');
insert into genres (id, name) values (2, 'Romance');
insert into genres (id, name) values (3, 'Science fiction');

insert into books (id, name, author_id, genre_id, year, volume) values (1, 'The Lord of the Rings', 1, 1, 1955, 2000);
insert into books (id, name, author_id, genre_id, year, volume) values (2, 'Pride and Prejudice', 2, 2, 1813, 500);
insert into books (id, name, author_id, genre_id, year, volume) values (3, 'His Dark Materials', 3, 1, 2000, 1800);
insert into books (id, name, author_id, genre_id, year, volume) values (4, 'The Hitchhiker''s Guide to the Galaxy', 4,
                                                                        3, 1979, 400);