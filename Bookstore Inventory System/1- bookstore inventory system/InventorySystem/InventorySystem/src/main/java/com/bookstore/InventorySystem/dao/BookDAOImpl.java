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
public class BookDAOImpl implements BookDAO{
    EntityManager entityManager;

    @Autowired
    public BookDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> q = entityManager.createQuery("From Book", Book.class);
        return q.getResultList();
    }

    @Override
    @Transactional
    public void createNewBook(Book book) {
        entityManager.persist(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        entityManager.merge(book);
    }


    @Override
    @Transactional
    public void deleteBook(int id) {
        Book book = getBookByID(id);
        entityManager.remove(book);
    }

    @Override
    public Book getBookByID(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> getBookByTitle(String name) {
        TypedQuery<Book> q = entityManager.createQuery("From Book Where title LIKE :name", Book.class);
        q.setParameter("name", "%"+name+"%");
        return q.getResultList();
    }

    @Override
    public List<Book> getBookByAuthor(int author) {
        Author authors = entityManager.find(Author.class, author);
        return entityManager.createQuery(
                        "SELECT b FROM Book b WHERE :author MEMBER OF b.authors", Book.class)
                .setParameter("author", authors)
                .getResultList();
    }

    @Override
    public List<Book> getBookByYearPublished(int year) {
        TypedQuery<Book> q = entityManager.createQuery("From Book Where yearPublished=:name", Book.class);
        q.setParameter("name", year);
        return q.getResultList();
    }

    @Override
    public List<Book> getBookByGenres(String genre) {
        TypedQuery<Book> q = entityManager.createQuery("From Book Where genres LIKE :name", Book.class);
        q.setParameter("name", "%"+genre+"%");
        return q.getResultList();
    }

    @Override
    public List<Book> getBooksByPublisher(int id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        TypedQuery<Book> q = entityManager.createQuery("From Book Where publisherId=:name", Book.class);
        q.setParameter("name", publisher);
        return q.getResultList();
    }



    @Override
    public Publisher checkBookPublisher(int id) {
        TypedQuery<Publisher> q = entityManager.createQuery("SELECT b.publisherId From Book b Where b.id=:id", Publisher.class);
        q.setParameter("id", id);

        return q.getSingleResult();
    }

    @Override
    public List<Author> checkBookAuthors(int id) {
        TypedQuery<Author> q = entityManager.createQuery("SELECT b.authors From Book b Where b.id=:id", Author.class);
        q.setParameter("id", id);
        return q.getResultList();
    }

    @Override
    @Transactional
    public void addBookPublisher(int id, int publisherId) {
        Publisher publisher = entityManager.find(Publisher.class, publisherId);
        Book book = getBookByID(id);
        book.setPublisherId(publisher);
        entityManager.persist(book);
    }

    @Override
    @Transactional
    public void addBookAuthors(int id, int authorId) {
        Author author = entityManager.find(Author.class, authorId);
        Book book = getBookByID(id);
        book.getAuthors().add(author);
        entityManager.persist(author);
    }

    @Override
    public int checkBookStock(int id) {
        return entityManager.createQuery("SELECT b.stock From Book b Where b.id=:id", Integer.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public float checkBookPrice(int id) {
        return entityManager.createQuery("SELECT b.price FROM Book b WHERE b.id = :id", Float.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
