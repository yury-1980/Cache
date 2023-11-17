package ru.clevertec.valid;

import ru.clevertec.dto.ClientDto;

public interface Validator {

    void validateClientDto(ClientDto clientDto);
}
