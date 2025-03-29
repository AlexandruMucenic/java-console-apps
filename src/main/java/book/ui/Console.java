package book.ui;

import java.util.Scanner;

import book.service.BookService;
import book.service.ClientService;

public class Console {
    private BookService bookService;
    private ClientService clientService;

    public Console(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    public void runConsole() {
        boolean running = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                printMenu();
                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        BookCrudMethods.printAllBooks(bookService);
                        break;
                    case "2":
                        BookCrudMethods.filterBooks(bookService);
                        break;
                    case "3":
                        BookCrudMethods.addBook(bookService);
                        break;
                    case "4":
                        BookCrudMethods.updateBook(bookService);
                        break;
                    case "5":
                        BookCrudMethods.deleteBook(bookService);
                        break;
                    case "6":
                        ClientCrudMethods.printAllClients(clientService);
                        break;
                    case "7":
                        ClientCrudMethods.filterClients(clientService);
                        break;
                    case "8":
                        ClientCrudMethods.addClient(clientService);
                        break;
                    case "9":
                        ClientCrudMethods.updateClient(clientService);
                        break;
                    case "10":
                        ClientCrudMethods.deleteClient(clientService);
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Print all books");
        System.out.println("2. Filter books by title");
        System.out.println("3. Add a book");
        System.out.println("4. Update a book");
        System.out.println("5. Delete a book");
        System.out.println("6. Print all clients");
        System.out.println("7. Filter clients by name");
        System.out.println("8. Add a client");
        System.out.println("9. Update a client");
        System.out.println("10. Delete a client");
        System.out.println("0. Exit");
    }
}
