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
import java.util.Optional;


@RestController
public class EmployerController {

    @Autowired
    EmployerService employerService;

    @PostMapping(value = "/employer")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Employer> create(@RequestBody Employer employer) {
        Employer result = employerService.addEmployer(employer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/employer")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public List<Employer> readAll(){
        return this.employerService.readAll();
    }

    @GetMapping(value = "/employer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<Employer> getById(@PathVariable(name = "id") Long id) {
        final Optional<Employer> employerOptional = employerService.getEmployerById(id);

        return employerOptional.isPresent()
                ? new ResponseEntity<>(employerOptional.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/employer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<Employer> update(@PathVariable(name = "id") Long id, @RequestBody Employer employer) {
        Employer updated = employerService.updateEmployer(employer, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping(value = "/employer/{id}/boss/{bossId}")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<Employer> update(@PathVariable(name = "id") Long id, @PathVariable(name = "bossId") Long bossId) {
        Employer updated = employerService.setEmployersBoss(bossId, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> delete(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(Integer.valueOf(employerService.deleteEmployer(id)), HttpStatus.OK);
    }
}




