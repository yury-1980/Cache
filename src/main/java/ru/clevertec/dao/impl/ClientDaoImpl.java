package ru.clevertec.dao.impl;

import ru.clevertec.dao.ClientDao;
import ru.clevertec.entity.Client;

import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

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
