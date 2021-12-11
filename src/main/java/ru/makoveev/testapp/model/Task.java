package ru.makoveev.testapp.model;

import lombok.Data;

@Data
public class Task {

    private Long id;
    private Integer priority;
    private String description;
    private Employer executor;

}
