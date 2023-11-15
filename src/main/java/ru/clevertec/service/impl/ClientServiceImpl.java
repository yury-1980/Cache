package ru.clevertec.service.impl;

import ru.clevertec.entity.Client;
import ru.clevertec.service.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    @Override
    public Optional<Client> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Client create(Client client) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Client client) {

    }
}
