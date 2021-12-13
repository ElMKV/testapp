package ru.makoveev.testapp.controller;

import liquibase.pro.packaged.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.service.EmployerService;

import java.util.List;


@RestController
public class EmployerController {
    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }


    @PostMapping(value = "/employer")
    public ResponseEntity<?> create(@RequestBody Employer employer) {
        employerService.create(employer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/employer")
    public ResponseEntity<List<Employer>> read() {
        final List<Employer> clients = employerService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}




