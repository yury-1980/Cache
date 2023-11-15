package ru.clevertec.dao;

import ru.clevertec.entity.Client;

import java.util.Optional;

public interface ClientDao {

    Optional<Client> findById(long id);

    Client create(Client client);

    void delete(long id);

    void update(Client client);
}
