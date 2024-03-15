package com.crudonorganisation.repositories;
import com.crudonorganisation.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}