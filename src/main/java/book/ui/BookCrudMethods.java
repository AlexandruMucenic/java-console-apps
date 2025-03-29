package book.ui;

import java.util.Scanner;
import java.util.Set;

import book.domain.Book;
import book.domain.validators.ValidatorException;
import book.service.BookService;

public class BookCrudMethods {
    public static void printAllBooks(BookService bookService) {
        System.out.println("All books:");
        Set<Book> books = bookService.getAllBooks();
        books.stream().forEach(System.out::println);
    }

    public static void filterBooks(BookService bookService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title to filter by:");
        String title = scanner.nextLine();
        Set<Book> books = bookService.filterBooksByTitle(title);
        books.stream().forEach(System.out::println);
    }

    public static void addBook(BookService bookService) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter book ID:");
            Long id = Long.parseLong(scanner.nextLine());
            System.out.println("Enter book title:");
            String title = scanner.nextLine();
            System.out.println("Enter book author:");
            String author = scanner.nextLine();
            System.out.println("Enter book ISBN:");
            String ISBN = scanner.nextLine();
            System.out.println("Enter book publication year:");
            int publicationYear = Integer.parseInt(scanner.nextLine());

            Book book = new Book(title, author, ISBN, publicationYear);
            book.setId(id);
            bookService.addBook(book);

            System.out.println("Book added successfully.");
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBook(BookService bookService) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter book ID to update:");
            Long id = Long.parseLong(scanner.nextLine());

            if (bookService.getAllBooks().stream().noneMatch(b -> b.getId().equals(id))) {
                System.out.println("No book found with ID " + id);
                return;
            }

            System.out.println("Enter new book title:");
            String title = scanner.nextLine();
            System.out.println("Enter new book author:");
            String author = scanner.nextLine();
            System.out.println("Enter new book ISBN:");
            String ISBN = scanner.nextLine();
            System.out.println("Enter new book publication year:");
            int publicationYear = Integer.parseInt(scanner.nextLine());

            Book book = new Book(title, author, ISBN, publicationYear);
            book.setId(id);

            bookService.updateBook(book);
            System.out.println("Book updated successfully.");
        } catch (ValidatorException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID or publication year. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteBook(BookService bookService) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter book ID to delete:");
            Long id = Long.parseLong(scanner.nextLine());
            bookService.deleteBook(id);
            System.out.println("Book deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
