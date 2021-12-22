package ru.makoveev.testapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    private Long id;
    private Integer priority;
    private String description;
    private Employer executor;


}
