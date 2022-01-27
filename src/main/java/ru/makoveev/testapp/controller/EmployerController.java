package ru.makoveev.testapp.controller;

import liquibase.pro.packaged.E;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.makoveev.testapp.dto.EmployerDTO;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.service.EmployerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class EmployerController {

    @Autowired
    EmployerService employerService;

    @PostMapping(value = "/employer")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<EmployerDTO> create(@RequestBody Employer employer) {
        Employer result = employerService.addEmployer(employer);
        return new ResponseEntity<>(convertToDTO(result), HttpStatus.CREATED);
    }

    @GetMapping(value = "/employer")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public List<EmployerDTO> readAll(){

        return employerService.readAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/employer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<EmployerDTO> getById(@PathVariable(name = "id") Long id) {
        final Optional<Employer> employerOptional = employerService.getEmployerById(id);

        return employerOptional.isPresent()
                ? new ResponseEntity<>(convertToDTO(employerOptional.get()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/employer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<EmployerDTO> update(@PathVariable(name = "id") Long id, @RequestBody Employer employer) {
        Employer updated = employerService.updateEmployer(employer, id);
        return new ResponseEntity<>(convertToDTO(updated), HttpStatus.OK);
    }

    @PutMapping(value = "/employer/{id}/boss/{bossId}")
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<EmployerDTO> update(@PathVariable(name = "id") Long id, @PathVariable(name = "bossId") Long bossId) {
        Employer updated = employerService.setEmployersBoss(bossId, id);
        return new ResponseEntity<>(convertToDTO(updated), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> delete(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(Integer.valueOf(employerService.deleteEmployer(id)), HttpStatus.OK);
    }

    private EmployerDTO convertToDTO(Employer employer) {
        EmployerDTO dto = EmployerDTO.builder()
                .id(employer.getId())
                .position(employer.getPosition())
                .fio(employer.getFio())
                .companyName(employer.getCompanyName())
                .build();
        if (employer.getBoss() !=null){
            dto.setBoss(convertToDTO(employer.getBoss()));
        }
        dto.setTasksCount(employerService.getTasksCount(employer.getId()));
        return dto;
    }
}




