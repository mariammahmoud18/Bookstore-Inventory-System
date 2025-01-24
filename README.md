# Bookstore-Inventory-System
The **Bookstore Inventory System** is a console-based application built using **Spring Boot 3.5** and **Java 23**. It is designed to manage inventory for a bookstore, allowing users to perform CRUD operations on three main entities: Publisher, Book, and Author. The system provides a variety of functionalities to manage relationships between these entities, such as adding books to publishers, authors to books, and more.
___
## Features
### Publisher Management
- **Create Publisher**: Add a new publisher to the system. <br>
- **Find Publisher by ID**: Retrieve a publisher by its unique ID. <br>
- **Find Publisher by Name**: Search for a publisher by name.<br>
- **Delete Publisher**: Remove a publisher from the system.<br>
- **Update Publisher**: Modify details of an existing publisher.<br>
- **Add Book to Publisher**: Associate a book with a publisher.<br>
- **Get Publisher Books**: Retrieve all books associated with a specific publisher.<br>
- **Get All Publishers**: List all publishers in the system.<br>

### Author Management
- **Create Author**: Add a new author to the system.<br>
- **Find Author by ID**: Retrieve an author by their unique ID.<br>
- **Find Author by First Name**: Search for authors by their first name.<br>
- **Find Author by Last Name**: Search for authors by their last name.<br>
- **Find Author by Nationality**: Search for authors by nationality.<br>
- **Delete Author**: Remove an author from the system.<br>
- **Update Author**: Modify details of an existing author.<br>
- **Get Author Books**: Retrieve all books written by a specific author.<br>
- **Add Author to Book**: Associate an author with a book.<br>

### Book Management
- **Create Book**: Add a new book to the system. <br>
- **Find Book by ID**: Retrieve a book by its unique ID.<br>
- **Find Book by Title**: Search for books by title.<br>
- **Find Book by Author**: Retrieve books written by a specific author.<br>
- **Find Book by Year Published**: Search for books by their publication year.<br>
- **Find Book by Genre**: Retrieve books by genre.<br>
- **Find Book by Publisher**: Retrieve books associated with a specific publisher.<br>
- **Delete Book**: Remove a book from the system.<br>
- **Update Book**: Modify details of an existing book.<br>
- **Get Book Stock**: Check the stock availability of a book.<br>
- **Get Book Price**: Retrieve the price of a book.<br>
- **Get Book Author/s**: Retrieve the author(s) of a book.<br>
-**Get Book Publisher**: Retrieve the publisher of a book.<br>
-**Add Author to Book**: Associate an author with a book.<br>
-**Add Publisher to Book**: Associate a publisher with a book.<br>
___
## Technologies Used
- **Java 23:** The core programming language used for the application.
- **Spring Boot:** Framework for building the application and managing dependencies.
- **Hibernate:** ORM (Object-Relational Mapping) for database interactions.
- **MySQL:** Relational database used for storing and managing data.
- **Maven:** Build automation and dependency management.
___
## Entity Relationships
### Publisher:
- A publisher can have multiple books.
- A book belongs to one publisher.

### Author:
- An author can write multiple books. 
- A book can have multiple authors.

### Book:
- A book is associated with one publisher and one or more authors.
___
## Class Diagram
 ![Bookstore Inventory System](https://github.com/user-attachments/assets/afbc4479-b0b9-4ba0-afe9-47c5b40c6400)
___
## Database Script
``CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Title`varchar(45) DEFAULT NULL,
  `year_published` year DEFAULT NULL,
    `price` integer DEFAULT NULL,
      `stock` integer DEFAULT NULL,
	genres VARCHAR(255) DEFAULT NULL,
      publisher_id integer,
    FOREIGN KEY (publisher_id) REFERENCES publisher(id) ON DELETE CASCADE,
  PRIMARY KEY (`id`)
);



CREATE TABLE `publisher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name`varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
   `phone` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
  

CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
   `bio` varchar(255) DEFAULT NULL,
   `nationality` varchar(255) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE book_authors (
    book_id INT,
    author_id INT,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);``


