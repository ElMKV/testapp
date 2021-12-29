package ru.makoveev.testapp;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import ru.makoveev.testapp.model.Tables;
import ru.makoveev.testapp.model.Task;
import ru.makoveev.testapp.repository.EmployersRepository;
import ru.makoveev.testapp.service.EmployerServiceImpl;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class TestAppApplication{


	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);

	}

}
