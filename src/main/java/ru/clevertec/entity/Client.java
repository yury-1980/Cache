package ru.clevertec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private long id;
    private String clientName;
    private String familyName;
    private String surName;
    LocalDate birthDay;
}