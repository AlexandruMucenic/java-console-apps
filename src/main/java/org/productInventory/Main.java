package org.productInventory;

import org.productInventory.domain.Product;
import org.productInventory.repository.ProductRepository;
import org.productInventory.repository.Repository;
import org.productInventory.service.ProductService;
import org.productInventory.ui.Console;

public class Main {
    public static void main(String[] args) {
        Repository<Long, Product> productRepository = new ProductRepository("./data/products");

        ProductService productService = new ProductService(productRepository);

        Console console = new Console(productService);
        console.runConsole();
    }
}