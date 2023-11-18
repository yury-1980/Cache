package ru.clevertec;

import ru.clevertec.dao.ClientDao;
import ru.clevertec.dao.ConnectionPoolManager;
import ru.clevertec.dao.impl.ClientDaoImpl;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.entity.Client;
import ru.clevertec.mapper.MapperClient;
import ru.clevertec.mapper.MapperClientImpl;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.impl.ClientServiceImpl;
import ru.clevertec.valid.Validator;
import ru.clevertec.valid.impl.ValidatorImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = Client.builder().clientName("Юра").familyName("Яшков").surName("Владимирович")
                .birthDay(LocalDate.parse("2001-01-01")).build();
        ClientDao clientDao = new ClientDaoImpl(new ConnectionPoolManager());
        MapperClient mapperClient = new MapperClientImpl();
        Validator validator = new ValidatorImpl();
        ClientDto clientDto = new ClientDto("Лёшаввыz", "Петров", "Петрович",
                LocalDate.parse("2000-01-01"));

        ClientService clientService = new ClientServiceImpl(clientDao, mapperClient, validator);
//        ClientDto byId = clientService.findById(1);
//        System.out.println("byId = " + byId);

        List<ClientDto> byAll = clientService.findByAll(3);
        System.out.println("byAll = " + byAll);
//
//        Client createClient = clientService.create(clientDto);
//        System.out.println("createClient = " + createClient);

        System.out.println(clientService.update(23, clientDto));

//        clientService.delete(23);


//        System.out.println(clientDao.findById(1));
    }
}