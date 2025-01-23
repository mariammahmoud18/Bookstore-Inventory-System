package com.bookstore.InventorySystem.dao;

import com.bookstore.InventorySystem.entities.Author;
import com.bookstore.InventorySystem.entities.Book;
import com.bookstore.InventorySystem.entities.Publisher;

import java.util.List;

public interface AuthorDAO {
    void createAuthor(Author author);
    void deleteAuthor(int id);
    void updateAuthor(Author author);
    Author findAuthorById(int id);
    List<Author> findAuthorsByFirstName(String name);
    List<Author> findAuthorsByLastName(String name);
    List<Author> findAuthorsByNationality(String name);
    List<Book> getAuthorBooks(int id);
    void addAuthorBook(int id, int bookId);
    List<Author> getAll();
}
