package com.dinsaren.hrmanagementsystemapplication.services;

import com.dinsaren.hrmanagementsystemapplication.models.Employee;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    List<Employee> getAllEmployeeStatusActive();
    List<Employee> getAllEmployeeAllStatus();
    void create(Employee employee);
    void update(Employee employee);
    Employee getEmployeeById(Long id);
    void delete(Long id);
    DataTablesOutput<Employee> findAllDatatable(DataTablesInput input);

}
