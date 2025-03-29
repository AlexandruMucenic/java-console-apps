package org.productInventory.repository;

import org.productInventory.domain.Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements Repository<Long, Product> {
    private String fileName;

    public ProductRepository(String fileName) {
        this.fileName = fileName;
    }

    private List<Product> loadData() {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get(fileName);
        try {
            Files.lines(path).forEach(line -> {
                String[] items = line.split(",");
                if (items.length == 4) {
                    try {
                        Long id = Long.valueOf(items[0]);
                        String name = items[1];
                        String brand = items[2];
                        String availability = items[3];

                        Product product = new Product(name, brand, availability);
                        product.setId(id);
                        products.add(product);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line: " + line);
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public Optional<Product> save(Product product) throws RuntimeException {
        if (product == null) {
            throw new IllegalArgumentException("Book must not be null.");
        }
        Optional<Product> existingProd = findOne(product.getId());
        if (existingProd.isPresent()) {
            return existingProd;
        }
        saveToFile(product);
        return Optional.empty();
    }

    private void saveToFile(Product product) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.newLine();
            bufferedWriter.write(product.getId() + "," + product.getName() + "," + product.getBrand() + ","
                    + product.getAvailability());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteFile(List<Product> products) {
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Product product : products) {
                bufferedWriter.write(product.getId() + "," + product.getName() + "," + product.getBrand() + ","
                        + product.getAvailability());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Product> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }
        List<Product> products = loadData();
        return products.stream().filter(prod -> prod.getId().equals(id)).findFirst();
    }

    @Override
    public Iterable<Product> findAll() {
        return loadData();
    }

    @Override
    public Optional<Product> delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        List<Product> products = loadData();
        Optional<Product> prodsToDelete = products.stream().filter(prod -> prod.getId().equals(id)).findFirst();

        if (prodsToDelete.isPresent()) {
            products.remove(prodsToDelete.get());
            rewriteFile(products);
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> update(Product entity) throws RuntimeException {
        return Optional.empty();
    }
}
