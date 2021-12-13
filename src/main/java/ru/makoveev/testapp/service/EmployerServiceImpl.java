package ru.makoveev.testapp.service;

import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Employer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployerServiceImpl implements EmployerService{
    // Хранилище работодателей
    private static final Map<Integer, Employer> EMPLOYER_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID работодателя
    private static final AtomicInteger EMPLOYER_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Employer employer) {
        final int employerId = EMPLOYER_ID_HOLDER.incrementAndGet();
        employer.setId((long) employerId);
        EMPLOYER_REPOSITORY_MAP.put(employerId, employer);

    }

    @Override
    public List<Employer> readAll() {
        return new ArrayList<>(EMPLOYER_REPOSITORY_MAP.values());
    }

    @Override
    public Employer read(int id) {
        return EMPLOYER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Employer employer, int id) {
        if (EMPLOYER_REPOSITORY_MAP.containsKey(id)) {
            employer.setId((long) id);
            EMPLOYER_REPOSITORY_MAP.put(id, employer);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return EMPLOYER_REPOSITORY_MAP.remove(id) != null;
    }
}
