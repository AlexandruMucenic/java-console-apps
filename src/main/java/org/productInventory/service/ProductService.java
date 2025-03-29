package org.productInventory.service;

import org.productInventory.domain.Product;
import org.productInventory.repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProductService {
    private Repository<Long, Product> productRepository;

    public ProductService(Repository<Long, Product> productRepository) {
        this.productRepository = productRepository;
    }

    public Set<Product> getAllProducts() {
        Iterable<Product> books = productRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void addProduct(Product product) throws RuntimeException {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) throws RuntimeException {
        productRepository.delete(id);
    }
}
