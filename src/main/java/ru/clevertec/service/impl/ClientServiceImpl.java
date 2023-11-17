package ru.clevertec.service.impl;

import lombok.AllArgsConstructor;
import ru.clevertec.dao.ClientDao;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.entity.Client;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.mapper.ClientMapper;
import ru.clevertec.service.ClientService;
import ru.clevertec.valid.Validator;

import java.util.List;

@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final Validator validator;

    @Override
    public ClientDto findById(long id) {
         return clientDao.findById(id)
                .map(clientMapper::toClientDto)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public List<ClientDto> findByAll() {
        return clientDao.findByAll().stream()
                .map(clientMapper::toClientDto)
                .toList();
    }

    @Override
    public Client create(ClientDto clientDto) {
        validator.validateClientDto(clientDto);
        Client client = clientMapper.toClient(clientDto);

        return clientDao.create(client);
    }

    @Override
    public Client update(long id, ClientDto clientDto) {
        validator.validateClientDto(clientDto);
        Client client = clientMapper.toClient(clientDto);

        return clientDao.update(id, client);
    }

    @Override
    public void delete(long id) {
        clientDao.delete(id);
    }
}
