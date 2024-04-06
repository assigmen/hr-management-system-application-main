package com.dinsaren.hrmanagementsystemapplication.services.impl;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Employee;
import com.dinsaren.hrmanagementsystemapplication.repositories.EmployeeRepository;
import com.dinsaren.hrmanagementsystemapplication.services.EmployeeService;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeeStatusActive() {
        return employeeRepository.findAllByStatusOrderByIdDesc(Constant.STATUS_ACTIVE);
    }

    @Override
    public List<Employee> getAllEmployeeAllStatus() {

        return employeeRepository.findAllByStatusInOrderByEmployeeNoAsc(Constant.getAllStatusString());
    }

    @Override
    public void create(Employee employee) {

        if(employee.getId() == 0){
            employee.setEmployeeNo("EPM-"+employeeRepository.findFirstByOrderByIdDesc().getId()+1);
        }
        employee.setStatus("ACT");
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        Employee find = employeeRepository.findByIdAndStatusIn(employee.getId(), Constant.getAllStatusString());
        if(find != null){
            employee.setStatus("ACT");
            employeeRepository.save(employee);
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findByIdAndStatusIn(id, Constant.getAllStatusString());
    }

    @Override
    public void delete(Long id) {
       Employee  findEmployee =  employeeRepository.findByIdAndStatusIn(id, Constant.getAllStatusString());
       if(findEmployee != null){
           findEmployee.setStatus("DEL");
           employeeRepository.save(findEmployee);
       }
    }

    @Override
    public DataTablesOutput<Employee> findAllDatatable(DataTablesInput input) {
        return employeeRepository.findAll(input);
    }

}
