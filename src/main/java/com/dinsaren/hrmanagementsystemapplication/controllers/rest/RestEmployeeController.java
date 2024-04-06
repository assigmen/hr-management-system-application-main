package com.dinsaren.hrmanagementsystemapplication.controllers.rest;

import com.dinsaren.hrmanagementsystemapplication.models.Employee;
import com.dinsaren.hrmanagementsystemapplication.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestEmployeeController {
    private final Logger log = LoggerFactory.getLogger(RestEmployeeController.class);
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public DataTablesOutput<Employee> findAllDatatable(@Valid DataTablesInput input) {
        log.debug("REST request to get a page of Employee");
        return employeeService.findAllDatatable(input);
    }

}
