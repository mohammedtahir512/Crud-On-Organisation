package com.crudonorganisation.controllers;

import com.crudonorganisation.exceptions.ResourceNotFoundException;
import com.crudonorganisation.models.Employee;
import com.crudonorganisation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }


    @GetMapping("/calculate-salary/{id}/{totalWorkingDays}/{absentDays}")
    public ResponseEntity<?> calculateEmployeeSalary(
            @PathVariable long id,
            @PathVariable int totalWorkingDays,
            @PathVariable int absentDays) {
        if (employeeService.getEmployeeById(id) == null) {
            String error = "No employee found with id: " + id;
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        } else {
            double salary = employeeService.calculateEmployeeSalary(id, totalWorkingDays, absentDays);
            return new ResponseEntity<>(salary, HttpStatus.OK);
        }

    }
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long employeeId) throws ResourceNotFoundException {
        Optional<Employee> employee=employeeService.getEmployeeById(employeeId);
        if(employee.isPresent()) return new ResponseEntity<>(employee,HttpStatus.OK);
        else {
            String error="no employee found with id: "+employeeId;
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employee=employeeService.getEmployeeById(employeeId);
        if(employee.isPresent()) {
            Employee employee1 = employeeService.updateEmployee(employeeId, updatedEmployee);
            return new ResponseEntity<>(employee1, HttpStatus.OK);
        }
        else {
            String error="no employee found with id: "+employeeId;
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {

        employeeService.deleteEmployee(employeeId);
        String message="successfully deleted employee with id: "+employeeId;
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}

