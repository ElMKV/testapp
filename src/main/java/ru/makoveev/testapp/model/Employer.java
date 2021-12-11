package ru.makoveev.testapp.model;

import lombok.Data;

@Data
public class Employer {
    private Long id;
    private Employer boss;
    private String position;
    private String fio;
    private String companyName;

}

