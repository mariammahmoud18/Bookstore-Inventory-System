package com.bookstore.InventorySystem.entities;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
public class Book {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    @Column(name = "Title", nullable = false)
    String title;

    @Column(name = "price", nullable = false)
    float price;

    @Column(name = "stock", nullable = false)
    int stock;

    @Column(name = "year_published", nullable = false)
    int yearPublished;

    @Column(name = "genres", nullable = false)
    String genres;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    Publisher publisherId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_authors", // Junction table for books and authors
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    List<Author> authors;

    public Book() {
    }

    public Book(String title, float price, int stock, int yearPublished, String genres) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        if(validateYearPublished(yearPublished))
            this.yearPublished = yearPublished;
        else
            throw new RuntimeException("Book Published Year Is Not a Valid Year - "+yearPublished);
        this.genres = genres;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getGenres() {
        return genres;
    }

    public Publisher getPublisherId() {
        return publisherId;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPublisherId(Publisher publisherId) {
        this.publisherId = publisherId;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Boolean validateYearPublished (int year){
        int currentYear = Year.now().getValue();
        return year <= currentYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", yearPublished=" + yearPublished +
                ", genres='" + genres + '\'' +
                ", publisher=" + publisherId+
               ", authors=' " + authors.stream().map(Author::getID).collect(Collectors.toList()) +
                '}';
    }
}
