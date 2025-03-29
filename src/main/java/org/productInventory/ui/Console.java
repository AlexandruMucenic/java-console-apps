package org.productInventory.ui;

import org.productInventory.domain.Product;
import org.productInventory.service.ProductService;

import java.util.Scanner;
import java.util.Set;

public class Console {
    private ProductService productService;

    public Console(ProductService productService) {
        this.productService = productService;

    }

    public void runConsole() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    running = false;
                    break;
                default:
                    if (command.contains("print-all")) {
                        printAllProducts();
                    }
                    if (command.contains("add")) {
                        addProduct(command);
                    }
                    if (command.contains("delete")) {
                        deleteProduct(command);
                    }
            }
        }
    }

    private void printMenu() {
        System.out.println("You can use the following commands (press 0 to exit):");
        System.out.println("print-all, add, delete");
    }

    public void printAllProducts() {
        System.out.println("All books:");
        Set<Product> products = productService.getAllProducts();
        products.stream().forEach(System.out::println);
    }

    public void addProduct(String command) {
        String[] instructions = command.split(" ");

        Set<Product> products = productService.getAllProducts();
        Product prodToAdd = new Product(instructions[1], instructions[2], instructions[3]);
        prodToAdd.setId(Long.valueOf(products.size() + 1));

        productService.addProduct(prodToAdd);
    }

    public void deleteProduct(String command) {
        String[] instructions = command.split(" ");
        Long id = Long.valueOf(instructions[1]);
        productService.deleteProduct(id);
    }
}
