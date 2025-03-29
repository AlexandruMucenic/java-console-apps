package book.domain.validators;

public class BookRentalException extends RuntimeException {
    public BookRentalException(String message) {
        super(message);
    }

    public BookRentalException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookRentalException(Throwable cause) {
        super(cause);
    }
}
