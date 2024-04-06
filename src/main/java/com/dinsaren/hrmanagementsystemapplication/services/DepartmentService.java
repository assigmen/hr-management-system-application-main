package com.dinsaren.hrmanagementsystemapplication.services;

import com.dinsaren.hrmanagementsystemapplication.models.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    List<Department> getAllDepartmentStatusActive();
    Department getById(Integer id);
    void update(Department req);
    void create(Department req);
}
