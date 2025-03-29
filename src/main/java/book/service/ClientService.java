package book.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import book.domain.Client;
import book.domain.validators.ValidatorException;
import book.repository.Repository;

public class ClientService {
    private Repository<Long, Client> clientRepository;

    public ClientService(Repository<Long, Client> repository) {
        this.clientRepository = repository;
    }

    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    public Set<Client> getAllClients() {
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Client> filterClientsByName(String s) {
        Iterable<Client> clients = clientRepository.findAll();

        return StreamSupport.stream(clients.spliterator(), false)
                .filter(client -> client.getFirstName().contains(s) || client.getLastName().contains(s))
                .collect(Collectors.toSet());
    }

    public void updateClient(Client client) throws ValidatorException {
        clientRepository.update(client).ifPresent(_ -> {
            throw new ValidatorException("Client with ID " + client.getId() + " does not exist.");
        });
    }

    public void deleteClient(Long id) {
        clientRepository.delete(id).ifPresent(_ -> {
            throw new ValidatorException("Client with ID " + id + " does not exist.");
        });
    }
}
