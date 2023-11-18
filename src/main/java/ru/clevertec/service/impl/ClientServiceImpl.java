package ru.clevertec.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.clevertec.dao.ClientDao;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.entity.Client;
import ru.clevertec.exception.ClientDtoNotValidate;
import ru.clevertec.exception.ClientNotFoundException;
import ru.clevertec.mapper.MapperClient;
import ru.clevertec.service.ClientService;
import ru.clevertec.valid.Validator;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private MapperClient mapperClient;
    private Validator validator;

    @Override
    public ClientDto findById(long id) {
        return clientDao.findById(id)
                .map(mapperClient::toClientDto)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public List<ClientDto> findByAll(long num) {
        return clientDao.findByAll().stream()
                .limit(num)
                .map(mapperClient::toClientDto)
                .toList();
    }

    @Override
    public Client create(ClientDto clientDto) {

        try {
            validator.validateClientDto(clientDto);
        } catch (ClientDtoNotValidate notValidate) {
            notValidate.printStackTrace();
        }

        Client client = mapperClient.toClient(clientDto);

        return clientDao.create(client);
    }

    @Override
    public Client update(long id, ClientDto clientDto) {

        try {
            validator.validateClientDto(clientDto);
        } catch (ClientDtoNotValidate notValidate) {
            notValidate.printStackTrace();
        }

        Client client = mapperClient.toClient(clientDto);

        return clientDao.update(id, client);
    }

    @Override
    public void delete(long id) {
        clientDao.delete(id);
    }
}
