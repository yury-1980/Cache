package ru.clevertec.service;

import ru.clevertec.entity.Client;

import java.util.Optional;

public interface ClientService {

    Optional<Client> findById(long id);

    Client create(Client client);

    void delete(long id);

    void update(Client client);
}
