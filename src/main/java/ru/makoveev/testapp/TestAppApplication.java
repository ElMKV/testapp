package ru.makoveev.testapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import ru.makoveev.testapp.repository.EmployersRepository;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class TestAppApplication implements CommandLineRunner {

	@Autowired
	private EmployersRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TestAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("hello");
		repository.insertEmployer();
		System.out.println(repository.getEmployersList().stream().findFirst().get().toString());
	}



}
