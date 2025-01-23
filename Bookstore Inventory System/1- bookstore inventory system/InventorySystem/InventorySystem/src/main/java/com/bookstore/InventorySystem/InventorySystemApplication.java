package com.bookstore.InventorySystem;

import com.bookstore.InventorySystem.dao.AuthorDAO;
import com.bookstore.InventorySystem.dao.BookDAO;
import com.bookstore.InventorySystem.dao.PublisherDAO;
import com.bookstore.InventorySystem.entities.Author;
import com.bookstore.InventorySystem.entities.Book;
import com.bookstore.InventorySystem.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;


import java.util.Scanner;

@SpringBootApplication
public class InventorySystemApplication {

	@Autowired
	private PublisherDAO publisherDAO;

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private AuthorDAO authorDAO;
	Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		SpringApplication.run(InventorySystemApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(String[] args){
		return runner-> {
			ProgramStart();
		};
	}

	void ProgramStart(){
		System.out.println("/////WELCOME TO LIBRARAY INVENTOY SYSTEM/////");
		System.out.println("To check out Books enter (1)");
		System.out.println("To check out Authors enter (2)");
		System.out.println("To check out Publishers enter (3)");
		System.out.println("To end system enter (4)");
		System.out.println("//////////////////////////////////");
		int choice = scanner.nextInt();
			if(choice == 3){
				publisherHandler(publisherDAO);
				ProgramStart();
			}
			else if(choice == 2){
				authorHandler(authorDAO);
				ProgramStart();
			}
			else if(choice == 1){
				bookHandler(bookDAO);
				ProgramStart();
			}
			else if(choice == 4)
			{
				System.exit(0);
			}




	}

	void publisherHandler(PublisherDAO publisherDAO){
		System.out.println("///////////////////////");
		System.out.println("To view all publishers enter (1)");
		System.out.println("To create a new publisher enter (2)");
		System.out.println("To update an existing publisher enter (3)");
		System.out.println("To delete a publisher enter (4)");
		System.out.println("To find a publisher by id enter (5)");
		System.out.println("To find a publisher by name enter (6)");
		System.out.println("To add a book to a publisher enter (7)");
		System.out.println("To get all publisher books enter (8)");
		System.out.println("To go back to main menu enter (9)");
		System.out.println("///////////////////////");

		int choice1 = scanner.nextInt();
		if(choice1 == 1){
			List<Publisher> ans = publisherDAO.getAll();
			for(Publisher p: ans){
				System.out.println(p.toString());
			}
			publisherHandler(publisherDAO);
		}
		else if(choice1 == 2){
			System.out.println("///////////////////");
			System.out.println("Enter the publishers's info [Name, Address, phone, Email]");
			System.out.println("///////////////////////");
			scanner.nextLine();
			String name = scanner.nextLine();
			String address = scanner.nextLine();
			String phone = scanner.nextLine();
			String email = scanner.nextLine();
			Publisher publisher = new Publisher(name, address, phone, email);
			publisherDAO.createPublisher(publisher);
			System.out.println("SUCCESSFULLY CREATED!");
			System.out.println("///////////////////////////");
			publisherHandler(publisherDAO);
		}
		else if(choice1 == 3){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Publisher");
			int id = scanner.nextInt();
			Publisher publisher = publisherDAO.findPublisherById(id);
			System.out.println("///////////////////");
			System.out.println("Enter the publishers's info [Name, Address, phone, Email]");
			System.out.println("///////////////////////");
			scanner.nextLine();
			String name = scanner.nextLine();
			String address = scanner.nextLine();
			String phone = scanner.nextLine();
			String email = scanner.nextLine();
			publisher.setAdress(address);
			publisher.setEmail(email);
			publisher.setPhone(phone);
			publisher.setName(name);
			publisherDAO.updatePublisher(publisher);
			System.out.println("SUCCESSFULLY UPDATED!");
			System.out.println("/////////////////////////");
			publisherHandler(publisherDAO);

		}
		else if(choice1 == 4){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Publisher");
			int id = scanner.nextInt();
			publisherDAO.deletePublisher(id);
			System.out.println("SUCCESSFULLY DELETED!");
			System.out.println("/////////////////////////");
			publisherHandler(publisherDAO);

		}
		else if(choice1 == 5){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Publisher");
			int id = scanner.nextInt();
			Publisher publisher = publisherDAO.findPublisherById(id);

			System.out.println(publisher.toString());
			System.out.println("/////////////////////////");
			publisherHandler(publisherDAO);

		}
		else if(choice1 == 6){
			System.out.println("///////////////////////");
			System.out.println("Enter the name of the Publisher/s");
			scanner.nextLine();
			String name = scanner.nextLine();
			List<Publisher> publishers = publisherDAO.findPublisherByName(name);
			for(Publisher p: publishers){
				System.out.println(p.toString());
			}
			System.out.println("/////////////////////////");
			publisherHandler(publisherDAO);

		}
		else if (choice1 == 7){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Publisher");
			int id = scanner.nextInt();
			System.out.println("Enter the ID of the Book");
			int bookId = scanner.nextInt();
			System.out.println("/////////////////////////");
			publisherDAO.addBooktoPublisher(id, bookId);
			publisherHandler(publisherDAO);
		}
		else if (choice1 == 8){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Publisher");
			int id = scanner.nextInt();
			System.out.println("/////////////////////////");
			System.out.println(publisherDAO.getPublisherBooks(id));
			publisherHandler(publisherDAO);
		}

		else if(choice1 == 9){
			ProgramStart();
		}

	}

	void authorHandler(AuthorDAO authorDAO){
		System.out.println("///////////////////////");
		System.out.println("To view all Authors enter (1)");
		System.out.println("To create a new author enter (2)");
		System.out.println("To update an existing author enter (3)");
		System.out.println("To delete an author enter (4)");
		System.out.println("To find an author by id enter (5)");
		System.out.println("To find an author by first name enter (6)");
		System.out.println("To find an author by last name enter (7)");
		System.out.println("To find an author by nationality enter (8)");
		System.out.println("To add a book to an author enter (9)");
		System.out.println("To get author's books enter (10)");
		System.out.println("To go back to main menu enter (11)");
		System.out.println("///////////////////////");

		int choice1 = scanner.nextInt();
		if(choice1 == 1){
			List<Author> ans = authorDAO.getAll();

				System.out.println(ans);

			authorHandler(authorDAO);
		}
		else if(choice1 == 2){
			System.out.println("///////////////////");
			System.out.println("Enter the author's info [First name, Last name, Birthdate, Nationality, Bio]");
			System.out.println("///////////////////////");
			scanner.nextLine();
			String firstName = scanner.nextLine();
			String lastName = scanner.nextLine();
			String birthdate = scanner.nextLine();
			String nationality = scanner.nextLine();
			String bio = scanner.nextLine();

			Author author = new Author(firstName, birthdate, lastName, nationality, bio);
			authorDAO.createAuthor(author);
			System.out.println("SUCCESSFULLY CREATED!");
			System.out.println("///////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 3){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Author");
			int id = scanner.nextInt();
			Author author = authorDAO.findAuthorById(id);
			System.out.println("///////////////////");
			System.out.println("Enter the author's info [First name, Last name, Birthdate, Nationality, Bio]");
			System.out.println("///////////////////////");
			scanner.nextLine();
			String firstName = scanner.nextLine();
			String lastName = scanner.nextLine();
			String birthdate = scanner.nextLine();
			String nationality = scanner.nextLine();
			String bio = scanner.nextLine();
			author.setBio(bio);
			author.setBirthdate(birthdate);
			author.setFirstName(firstName);
			author.setNationality(nationality);
			author.setLastName(lastName);
			authorDAO.updateAuthor(author);
			System.out.println("SUCCESSFULLY UPDATED!");
			System.out.println("/////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 4){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Author");
			int id = scanner.nextInt();
			authorDAO.deleteAuthor(id);
			System.out.println("SUCCESSFULLY DELETED!");
			System.out.println("/////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 5){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Author");
			int id = scanner.nextInt();
			Author author = authorDAO.findAuthorById(id);

			System.out.println(author.toString());
			System.out.println("/////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 6){
			System.out.println("///////////////////////");
			System.out.println("Enter the first name of the Author/s");
			scanner.nextLine();
			String name = scanner.nextLine();
			List<Author> authors = authorDAO.findAuthorsByFirstName(name);
			for(Author a: authors){
				System.out.println(a.toString());
			}
			System.out.println("/////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 7){
			System.out.println("///////////////////////");
			System.out.println("Enter the last name of the Author/s");
			scanner.nextLine();
			String name = scanner.nextLine();
			List<Author> authors = authorDAO.findAuthorsByLastName(name);
			for(Author a: authors){
				System.out.println(a.toString());
			}
			System.out.println("/////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 8){
			System.out.println("///////////////////////");
			System.out.println("Enter the nationality of the Author/s");
			scanner.nextLine();
			String nationality = scanner.nextLine();
			List<Author> authors = authorDAO.findAuthorsByNationality(nationality);
			for(Author a: authors){
				System.out.println(a.toString());
			}
			System.out.println("/////////////////////////");
			authorHandler(authorDAO);
		}
		else if(choice1 == 9){
				System.out.println("///////////////////////");
				System.out.println("Enter the ID of the Author");
				int id = scanner.nextInt();
				System.out.println("Enter the ID of the Book");
				int bookId = scanner.nextInt();
				System.out.println("/////////////////////////");
				authorDAO.addAuthorBook(id, bookId);
				authorHandler(authorDAO);
		}
		else if (choice1 == 10){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Author");
			int id = scanner.nextInt();
			System.out.println("/////////////////////////");
			System.out.println(authorDAO.getAuthorBooks(id));
			authorHandler(authorDAO);
		}
		else if(choice1 == 11){
			ProgramStart();
		}
	}

	void bookHandler(BookDAO bookDAO){
		System.out.println("///////////////////////");
		System.out.println("To view all Books enter (1)");
		System.out.println("To create a new book enter (2)");
		System.out.println("To update an existing book enter (3)");
		System.out.println("To delete a book enter (4)");
		System.out.println("To find a book by id enter (5)");
		System.out.println("To find a book by title enter (6)");
		System.out.println("To find an book by genre/s enter (7)");
		System.out.println("To find a book by published year (8)");
		System.out.println("To find a book by author (9)");
		System.out.println("To find a book by publisher (10)");
		System.out.println("To check a book's stock enter (11)");
		System.out.println("To check a book's price enter (12)");
		System.out.println("To check a book's publisher (13)");
		System.out.println("To check a book's author/s enter (14)");
		System.out.println("To add book author enter (15)");
		System.out.println("To add book publisher enter (16)");
		System.out.println("To go back to main menu enter (17)");
		System.out.println("///////////////////////");

		int choice = scanner.nextInt();
		if(choice == 1){
			System.out.println(bookDAO.getAll());
			bookHandler(bookDAO);
		}
		else if(choice == 2){
			System.out.println("///////////////////");
			System.out.println("Enter the book's info [ title, year published, price, stock, genres]");
			System.out.println("///////////////////////");
			scanner.nextLine();
			String title = scanner.nextLine();
			int year = scanner.nextInt();
			float price = scanner.nextFloat();
			int stock = scanner.nextInt();
			String genres = scanner.nextLine();

			Book book = new Book(title,price,stock,year,genres);
			bookDAO.createNewBook(book);
			System.out.println("SUCCESSFULLY CREATED!");
			System.out.println("///////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 3){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Author");
			int id = scanner.nextInt();
			Author author = authorDAO.findAuthorById(id);
			System.out.println("///////////////////");
			System.out.println("Enter the author's info [First name, Last name, Birthdate, Nationality, Bio]");
			System.out.println("///////////////////////");
			scanner.nextLine();
			String firstName = scanner.nextLine();
			String lastName = scanner.nextLine();
			String birthdate = scanner.nextLine();
			String nationality = scanner.nextLine();
			String bio = scanner.nextLine();
			author.setBio(bio);
			author.setBirthdate(birthdate);
			author.setFirstName(firstName);
			author.setNationality(nationality);
			author.setLastName(lastName);
			authorDAO.updateAuthor(author);
			System.out.println("SUCCESSFULLY UPDATED!");
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 4){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			bookDAO.deleteBook(id);
			System.out.println("SUCCESSFULLY DELETED!");
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 5){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println(bookDAO.getBookByID(id).toString());
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 6){
			System.out.println("///////////////////////");
			System.out.println("Enter the title of the Book");
			scanner.nextLine();
			String title = scanner.nextLine();
			System.out.println(bookDAO.getBookByTitle(title));
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 7){
			System.out.println("///////////////////////");
			System.out.println("Enter the genre/s of the Book");
			String genres = scanner.nextLine();
			System.out.println(bookDAO.getBookByGenres(genres));
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 8){
			System.out.println("///////////////////////");
			System.out.println("Enter the published year of the Book/s");
			scanner.nextLine();
			int year = scanner.nextInt();
			List<Book> books = bookDAO.getBookByYearPublished(year);
			for(Book b: books){
				System.out.println(b.toString());
			}
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if (choice == 9){

			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the author");
			int id = scanner.nextInt();
			System.out.println(bookDAO.getBookByAuthor(id));
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}
		else if(choice == 10){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the publisher");
			int id = scanner.nextInt();
			System.out.println(bookDAO.getBooksByPublisher(id));
			System.out.println("/////////////////////////");
			bookHandler(bookDAO);
		}

		else if (choice == 11){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println("The Avilable Stock of the Book is: " + bookDAO.checkBookStock(id));
			bookHandler(bookDAO);
		}
		else if (choice == 12){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println(bookDAO.checkBookPrice(id));
			bookHandler(bookDAO);
		}
		else if (choice == 13){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println(bookDAO.checkBookPublisher(id));
			bookHandler(bookDAO);
		}
		else if (choice == 14){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println(bookDAO.checkBookAuthors(id));
			bookHandler(bookDAO);
		}
		else if (choice == 15){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println("Enter the ID of the Author");
			int authorId = scanner.nextInt();
			bookDAO.addBookAuthors(id, authorId);
			bookHandler(bookDAO);
		}

		else if(choice == 16){
			System.out.println("///////////////////////");
			System.out.println("Enter the ID of the Book");
			int id = scanner.nextInt();
			System.out.println("Enter the ID of the Publisher");
			int publisherId = scanner.nextInt();
			bookDAO.addBookPublisher(id, publisherId);
			bookHandler(bookDAO);
		}
		else if(choice == 17){
			ProgramStart();
		}
	}
}
