package ru.makoveev.testapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployerDTO {
    private Long id;
    private EmployerDTO boss;
    private String position;
    private String fio;
    private String companyName;
    private Integer tasksCount;
}

