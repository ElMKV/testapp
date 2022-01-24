package ru.makoveev.testapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employer {
    private Long id;
    private Employer boss;
    private String position;
    private String fio;
    private String companyName;
}

