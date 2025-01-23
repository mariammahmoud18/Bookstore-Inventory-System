package com.bookstore.InventorySystem.dao;

import com.bookstore.InventorySystem.entities.Book;
import com.bookstore.InventorySystem.entities.Publisher;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDAOImpl implements PublisherDAO{
    EntityManager entityManager;

    @Autowired
    public PublisherDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createPublisher(Publisher publisher) {
    entityManager.persist(publisher);
    }

    @Override
    @Transactional
    public void deletePublisher(int id) {
        Publisher publisher = findPublisherById(id);
        if (publisher != null) {
            entityManager.remove(publisher);
        } else {
            throw new EntityNotFoundException("Publisher with ID " + id + " not found.");
        }    }

    @Override
    public Publisher findPublisherById(Integer id) {
        return entityManager.find(Publisher.class, id);
    }

    @Override
    public List<Publisher> findPublisherByName(String name) {
        TypedQuery<Publisher> query = entityManager.createQuery("FROM Publisher WHERE name LIKE :theData", Publisher.class);
        query.setParameter("theData","%"+name+"%");

        return query.getResultList();
    }

    @Override
    @Transactional
    public void updatePublisher(Publisher publisher) {
        entityManager.merge(publisher);
    }

    @Override
    public List<Publisher> getAll() {
        TypedQuery<Publisher> query = entityManager.createQuery("From Publisher", Publisher.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addBooktoPublisher(int id, int bookId) {
        Publisher publisher = findPublisherById(id);
        if(publisher == null)
            throw new EntityNotFoundException("Publisher with ID " + id + " not found.");
        Book book = entityManager.find(Book.class, bookId);
        if(book == null)
            throw new EntityNotFoundException("Book with ID " + bookId + " not found.");
        book.setPublisherId(publisher);
        entityManager.persist(book);
    }

    @Override
    public List<Book> getPublisherBooks(int id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        String jpql = "SELECT b FROM Book b WHERE b.publisherId = :id";
        return entityManager.createQuery(jpql, Book.class)
                .setParameter("id", publisher)
                .getResultList();
    }
}
