package ru.makoveev.testapp.controller;

import liquibase.pro.packaged.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/employer/{id}")
    public ResponseEntity<Employer> read(@PathVariable(name = "id") int id) {
        final Employer employer = employerService.read(id);

        return employer != null
                ? new ResponseEntity<>(employer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/employer/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Employer employer) {
        final boolean updated = employerService.update(employer, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/employer/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = employerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}




