package book.repository;

import java.sql.*;
import java.util.*;

import book.domain.Client;
import book.domain.validators.Validator;
import book.domain.validators.ValidatorException;

public class ClientJdbcRepository implements Repository<Long, Client> {
    private String url;
    private String user;
    private String password;
    private Validator<Client> validator;

    public ClientJdbcRepository(Validator<Client> validator, String url, String user, String password) {
        this.validator = validator;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phone_number");

        Client client = new Client(firstName, lastName, email, phoneNumber);
        client.setId(id);
        return client;
    }

    @Override
    public Optional<Client> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        String query = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Client client = mapResultSetToClient(rs);
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Client client = mapResultSetToClient(rs);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Optional<Client> save(Client client) throws ValidatorException {
        if (client == null) {
            throw new IllegalArgumentException("Client must not be null.");
        }
        validator.validate(client);

        Optional<Client> existingClient = findOne(client.getId());
        if (existingClient.isPresent()) {
            return existingClient;
        }

        String query = "INSERT INTO clients (id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, client.getId());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getLastName());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client client) throws ValidatorException {
        if (client == null) {
            throw new IllegalArgumentException("Client must not be null.");
        }
        validator.validate(client);

        Optional<Client> existingClient = findOne(client.getId());
        if (existingClient.isEmpty()) {
            return existingClient;
        }

        String query = "UPDATE clients SET first_name = ?, last_name = ?, email = ?, phone_number = ? WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getLastName());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPhoneNumber());
            stmt.setLong(5, client.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null.");
        }

        Optional<Client> clientToDelete = findOne(id);
        if (clientToDelete.isPresent()) {
            String query = "DELETE FROM clients WHERE id = ?";
            try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}