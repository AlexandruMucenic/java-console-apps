package book.ui;

import java.util.Scanner;
import java.util.Set;

import book.domain.Client;
import book.domain.validators.ValidatorException;
import book.service.ClientService;

public class ClientCrudMethods {
    public static void printAllClients(ClientService clientService) {
        System.out.println("All clients:");
        Set<Client> clients = clientService.getAllClients();
        clients.forEach(System.out::println);
    }

    public static void filterClients(ClientService clientService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name to filter by:");
        Set<Client> clients = clientService.filterClientsByName(scanner.nextLine());
        clients.forEach(System.out::println);
    }

    public static void addClient(ClientService clientService) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter client ID:");
            Long id = Long.parseLong(scanner.nextLine());
            System.out.println("Enter client first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter client last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter client email:");
            String email = scanner.nextLine();
            System.out.println("Enter client phone number:");
            String phoneNumber = scanner.nextLine();

            Client client = new Client(firstName, lastName, email, phoneNumber);
            client.setId(id);
            clientService.addClient(client);

            System.out.println("Client added successfully.");
        } catch (ValidatorException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID. Please enter a valid number.");
        }
    }

    public static void updateClient(ClientService clientService) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter client ID to update:");
            Long id = Long.parseLong(scanner.nextLine());

            if (clientService.getAllClients().stream().noneMatch(c -> c.getId().equals(id))) {
                System.out.println("No client found with ID " + id);
                return;
            }

            System.out.println("Enter new client first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter new client last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter new client email:");
            String email = scanner.nextLine();
            System.out.println("Enter new client phone number:");
            String phoneNumber = scanner.nextLine();

            Client client = new Client(firstName, lastName, email, phoneNumber);
            client.setId(id);

            clientService.updateClient(client);
            System.out.println("Client updated successfully.");
        } catch (ValidatorException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteClient(ClientService clientService) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter client ID to delete:");
            Long id = Long.parseLong(scanner.nextLine());
            clientService.deleteClient(id);
            System.out.println("Client deleted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
