package com.example.salarymanagementsystem.service;

import com.example.salarymanagementsystem.model.Employee;
import com.example.salarymanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }
    public Optional<Employee> findById(int id) {
        return repository.findById(id);
    }
    public Employee save(Employee employee) {
        return repository.save(employee);
    }
    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public List<Employee> findByName(String name) {
        return repository.findByNameContaining(name);
    }

    public List<Employee> searchByName(String name) {
        return repository.findByNameContaining(name);
    }
}
