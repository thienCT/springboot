package com.example.salarymanagementsystem.controller;

import ch.qos.logback.core.model.Model;
import com.example.salarymanagementsystem.model.Employee;
import com.example.salarymanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addText("employees: " + employees);
        return "employees/list";
    }
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addText("employee");
        return "employees/form";
    }
    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping ("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Employee ID: "+id));
        model.addText("employee");
        return"employees/form";
        }
    @PostMapping ("/{id}")
    public String updateEmployee(@PathVariable int id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping ("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
    @GetMapping("/search")
    public String searchEmployees(@RequestParam String query, Model model) {
      List<Employee> employees = employeeService.searchByName(query);
        model.addText ("employees: " + employees);
        return "employees/list";
    }
}
