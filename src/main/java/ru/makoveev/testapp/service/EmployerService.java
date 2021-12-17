package ru.makoveev.testapp.service;

import ru.makoveev.testapp.model.Employer;

import java.util.List;

public interface EmployerService {

    void create(Employer employer);

    List<Employer> readAll(Employer employer);

    Employer read(int id);

    boolean update(Employer employer, int id);

    boolean delete(int id);


}
