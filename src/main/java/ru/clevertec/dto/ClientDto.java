package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    @NotNull
    @Pattern(regexp = "^[а-яА-Я\\s]{2,10}$")
    private String clientName;

    @NotNull
    @Pattern(regexp = "^[а-яА-Я\\s]{2,20}$")
    private String familyName;

    @NotNull
    @Pattern(regexp = "^[а-яА-Я\\s]{2,10}$")
    private String surname;

    @NotNull
    @Pattern(regexp = "((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]{1})")
    LocalDate birthday;
}
