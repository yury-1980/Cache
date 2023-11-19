package ru.clevertec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.clevertec.dao.ClientDao;
import ru.clevertec.dao.ConnectionPoolManager;
import ru.clevertec.dao.impl.ClientDaoImpl;
import ru.clevertec.dto.ClientDto;
import ru.clevertec.entity.Client;
import ru.clevertec.gson.LocalDateAdapter;
import ru.clevertec.gson.LocalDateSerializer;
import ru.clevertec.gson.OffsetDateTimeAdapter;
import ru.clevertec.gson.OffsetDateTimeSerializer;
import ru.clevertec.mapper.MapperClient;
import ru.clevertec.mapper.MapperClientImpl;
import ru.clevertec.serializator.SerializatorXML;
import ru.clevertec.serializator.impl.SerializatorXMLImpl;
import ru.clevertec.service.ClientService;
import ru.clevertec.service.impl.ClientServiceImpl;
import ru.clevertec.valid.Validator;
import ru.clevertec.valid.impl.ValidatorImpl;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class Main {
    public static void main(String[] args) {

        Validator validator = new ValidatorImpl();
        ClientDao clientDao = new ClientDaoImpl(new ConnectionPoolManager());
        MapperClient mapperClient = new MapperClientImpl();
        ClientService clientService = new ClientServiceImpl(clientDao, mapperClient, validator);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeSerializer())
                .create();
        String json;

        ClientDto clientDto = new ClientDto("Пётр", "Петров", "Петрович",
                LocalDate.parse("2000-01-01"));
        ClientDto clientDtoTwo = new ClientDto("Николай", "Николаев", "Николаевич",
                LocalDate.parse("2000-01-01"));

        // 1. Создание объекта.
        Client clientFirst = clientService.create(clientDto);
        json = gson.toJson(clientFirst);
        System.out.println("clientFirst = " + json);

        // 2. Получение объекта
        ClientDto clientDtoFirst = clientService.findById(1);
        json = gson.toJson(clientDtoFirst);
        System.out.println("clientDtoFirst = " + json);

        // 3. Изменение объекта
        Client update = clientService.update(1, clientDtoTwo);
        json = gson.toJson(update);
        System.out.println("update = " + json);

        SerializatorXML serializatorXML = new SerializatorXMLImpl();
        String serialize = serializatorXML.serialize(clientDtoTwo);
        System.out.println("serialize = " + serialize);
    }
}