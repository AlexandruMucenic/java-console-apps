package book.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import book.domain.Book;
import book.domain.validators.ValidatorException;
import book.repository.Repository;

public class BookService {
    private Repository<Long, Book> bookRepository;

    public BookService(Repository<Long, Book> repository) {
        this.bookRepository = repository;
    }

    public void addBook(Book book) throws ValidatorException {
        bookRepository.save(book);
    }

    public Set<Book> getAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Book> filterBooksByTitle(String s) {
        Iterable<Book> books = bookRepository.findAll();

        return StreamSupport.stream(books.spliterator(), false)
                .filter(book -> book.getTitle().contains(s)).collect(Collectors.toSet());
    }

    public void updateBook(Book book) throws ValidatorException {
        bookRepository.update(book).ifPresent(_ -> {
            throw new ValidatorException("Book with ID " + book.getId() + " does not exist.");
        });
    }

    public void deleteBook(Long id) {
        bookRepository.delete(id).ifPresent(_ -> {
            throw new ValidatorException("Book with ID " + id + " does not exist.");
        });
    }
}
