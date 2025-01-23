package com.bookstore.InventorySystem.dao;

import com.bookstore.InventorySystem.entities.Book;
import com.bookstore.InventorySystem.entities.Publisher;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PublisherDAO {
    void createPublisher(Publisher publisher);
    void deletePublisher(int id);
    Publisher findPublisherById(Integer id);
    List<Publisher> findPublisherByName(String name);
    void updatePublisher(Publisher publisher);
    List<Publisher> getAll();
    void addBooktoPublisher(int id,int bookId);
    List<Book> getPublisherBooks(int id);
}
