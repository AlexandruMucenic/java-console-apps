package book.repository;

import java.sql.*;
import java.util.*;

import book.domain.Book;
import book.domain.validators.Validator;
import book.domain.validators.ValidatorException;

public class BookJdbcRepository implements Repository<Long, Book> {
    private String url;
    private String user;
    private String password;
    private Validator<Book> validator;

    public BookJdbcRepository(Validator<Book> validator, String url, String user, String password) {
        this.validator = validator;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Optional<Book> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Book book = mapResultSetToBook(rs);
                    return Optional.of(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = mapResultSetToBook(rs);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Optional<Book> save(Book book) throws ValidatorException {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null.");
        }
        validator.validate(book);

        Optional<Book> existingBook = findOne(book.getId());
        if (existingBook.isPresent()) {
            return existingBook;
        }

        String query = "INSERT INTO books (id, title, author, isbn, publication_year) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getISBN());
            stmt.setInt(5, book.getPublicationYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Book book) throws ValidatorException {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null.");
        }
        validator.validate(book);

        Optional<Book> existingBook = findOne(book.getId());
        if (existingBook.isEmpty()) {
            return existingBook;
        }

        String query = "UPDATE books SET title = ?, author = ?, isbn = ?, publication_year = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getISBN());
            stmt.setInt(4, book.getPublicationYear());
            stmt.setLong(5, book.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        Optional<Book> bookToDelete = findOne(id);
        if (bookToDelete.isPresent()) {
            String query = "DELETE FROM books WHERE id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String isbn = rs.getString("isbn");
        int publicationYear = rs.getInt("publication_year");

        Book book = new Book(title, author, isbn, publicationYear);
        book.setId(id);
        return book;
    }
}
