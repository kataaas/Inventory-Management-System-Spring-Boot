package ru.kataaas.ims.service;

import org.springframework.stereotype.Service;
import ru.kataaas.ims.entity.EmployeeEntity;
import ru.kataaas.ims.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity findByLogin(String login) {
        return employeeRepository.findByLogin(login);
    }

}
