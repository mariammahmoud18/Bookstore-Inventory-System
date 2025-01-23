package com.bookstore.InventorySystem.dao;

import com.bookstore.InventorySystem.entities.Author;
import com.bookstore.InventorySystem.entities.Book;
import com.bookstore.InventorySystem.entities.Publisher;

import java.util.List;
public interface BookDAO {
    List<Book> getAll();
    void createNewBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
    Book getBookByID(int id);
    List<Book> getBookByTitle(String name);
    List<Book> getBookByAuthor(int author);
    List<Book> getBookByYearPublished(int year);
    List<Book> getBookByGenres(String genre);
    List<Book> getBooksByPublisher(int id);
    int checkBookStock(int id);
    float checkBookPrice(int id);
    Publisher checkBookPublisher(int id);
    List<Author> checkBookAuthors(int id);
    void addBookPublisher(int id, int publisherId);
    void addBookAuthors(int id, int authorId);

}
