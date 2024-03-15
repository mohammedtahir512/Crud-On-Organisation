package com.crudonorganisation.services;

import com.crudonorganisation.exceptions.ResourceNotFoundException;
import com.crudonorganisation.models.Employee;
import com.crudonorganisation.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        
        Optional<Employee> employee=employeeRepository.findById(employeeId);
        if(employee.isPresent()) return employee;
        else throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
        
        
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employeeId);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setAge(updatedEmployee.getAge());
            existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());

            return employeeRepository.save(existingEmployee);
        } else {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

        public double calculateEmployeeSalary (long id, int totalWorkingDays, int absentDays){
            Optional<Employee> employeeOptional = (getEmployeeById(id));

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                double baseSalary = employee.getSalary();
                double dailySalary = baseSalary / totalWorkingDays; // Assuming salary is divided equally for each working day
                double deduction = dailySalary * absentDays;
                return baseSalary - deduction;
            } else {
                throw new RuntimeException("Employee not found with id: " + id);


            }
        }

    }

