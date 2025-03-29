package book.domain.validators;

import java.util.regex.Pattern;

import book.domain.Client;

public class ClientValidator implements Validator<Client> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+\\d{2}-\\d{9}$");

    @Override
    public void validate(Client client) throws ValidatorException {
        if (client == null) {
            throw new ValidatorException("Client cannot be null.");
        }

        validateFirstName(client.getFirstName());
        validateLastName(client.getLastName());
        validateEmail(client.getEmail());
        validatePhoneNumber(client.getPhoneNumber());
    }

    private void validateFirstName(String firstName) throws ValidatorException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new ValidatorException("First name cannot be null or empty.");
        }
        if (firstName.length() > 50) {
            throw new ValidatorException("First name cannot exceed 50 characters.");
        }
    }

    private void validateLastName(String lastName) throws ValidatorException {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new ValidatorException("Last name cannot be null or empty.");
        }
        if (lastName.length() > 50) {
            throw new ValidatorException("Last name cannot exceed 50 characters.");
        }
    }

    private void validateEmail(String email) throws ValidatorException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidatorException("Email cannot be null or empty.");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidatorException("Invalid email format.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) throws ValidatorException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new ValidatorException("Phone number cannot be null or empty.");
        }
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new ValidatorException("Phone number must be a 10-digit numeric value.");
        }
    }
}
