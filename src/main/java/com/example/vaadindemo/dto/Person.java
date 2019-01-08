package com.example.vaadindemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private final String name;
    private final int id;
    private final int year;
}
