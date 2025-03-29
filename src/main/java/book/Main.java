package book;

import book.domain.Book;
import book.domain.Client;
import book.domain.validators.BookValidator;
import book.domain.validators.ClientValidator;
import book.domain.validators.Validator;
import book.repository.BookJdbcRepository;
import book.repository.ClientJdbcRepository;
import book.repository.Repository;
import book.service.BookService;
import book.service.ClientService;
import book.ui.Console;

public class Main {
    public static void main(String[] args) {
        Validator<Book> bookValidator = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();

        Repository<Long, Book> bookFileRepository = new BookJdbcRepository(bookValidator, "jdbc:postgresql://localhost:5432/book-store","", "");
        Repository<Long, Client> clientFileRepository = new ClientJdbcRepository(clientValidator, "jdbc:postgresql://localhost:5432/book-store","", "");

        BookService bookService = new BookService(bookFileRepository);
        ClientService clientService = new ClientService(clientFileRepository);

        Console console = new Console(bookService, clientService);
        console.runConsole();
    }
}