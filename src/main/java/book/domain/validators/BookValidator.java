package book.domain.validators;

import java.time.LocalDate;
import java.util.regex.Pattern;

import book.domain.Book;

public class BookValidator implements Validator<Book> {
    private static final Pattern ISBN_PATTERN = Pattern.compile("^(\\d{3}-\\d-\\d{2}-\\d{6}-\\d|\\d{3}-\\d-\\d{3}-\\d{5}-\\d)$");

    @Override
    public void validate(Book book) throws ValidatorException {
        if (book == null) {
            throw new ValidatorException("Book cannot be null");
        }

        // Title
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new ValidatorException("Title cannot be null or empty");
        }
        if (book.getTitle().length() > 255) {
            throw new ValidatorException("Title cannot exceed 255 characters");
        }

        // Author
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new ValidatorException("Author cannot be null or empty");
        }
        if (book.getAuthor().length() > 255) {
            throw new ValidatorException("Author cannot exceed 255 characters");
        }

        // ISBN
        if (book.getISBN() == null || !ISBN_PATTERN.matcher(book.getISBN()).matches()) {
            throw new ValidatorException("ISBN must be a valid 10 or 13 digit number");
        }

        // Publication year
        if (book.getPublicationYear() == 0) {
            throw new ValidatorException("Publication year cannot be 0");
        }

        int currentYear = LocalDate.now().getYear();
        if (book.getPublicationYear() > currentYear) {
            throw new ValidatorException("Publication year cannot be in the future");
        }
    }
}