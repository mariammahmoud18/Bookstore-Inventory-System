package com.bookstore.InventorySystem.dao;

import com.bookstore.InventorySystem.entities.Author;
import com.bookstore.InventorySystem.entities.Book;
import com.bookstore.InventorySystem.entities.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO{
    EntityManager entityManager;

    @Autowired
    public AuthorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createAuthor(Author author) {
        entityManager.persist(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(int id) {
        Author author = findAuthorById(id);
        entityManager.remove(author);
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        entityManager.merge(author);
    }

    @Override
    public Author findAuthorById(int id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAuthorsByFirstName(String name) {
        TypedQuery<Author> q = entityManager.createQuery("FROM Author WHERE firstName=:theData", Author.class);
        q.setParameter("theData", "%"+name+"%");
        return q.getResultList();
    }

    @Override
    public List<Author> findAuthorsByLastName(String name) {
        TypedQuery<Author> q = entityManager.createQuery("FROM Author WHERE lastName=:theData", Author.class);
        q.setParameter("theData", "%"+name+"%");
        return q.getResultList();
    }

    @Override
    public List<Author> findAuthorsByNationality(String name) {
        TypedQuery<Author> q = entityManager.createQuery("FROM Author WHERE nationality=:theData", Author.class);
        q.setParameter("theData", name);
        return q.getResultList();
    }


    @Override
    @Transactional
    public void addAuthorBook(int id, int bookId) {
        Author author = findAuthorById(id);
        Book book = entityManager.find(Book.class, bookId);
        book.getAuthors().add(author);
        entityManager.persist(book);
    }

    @Override
    public List<Book> getAuthorBooks(int id) {
        String jpql = "SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("authorId", id);
        return query.getResultList();
    }

    @Override
    public List<Author> getAll(){
        TypedQuery<Author> query = entityManager.createQuery("From Author", Author.class);
        return query.getResultList();
    }
}
