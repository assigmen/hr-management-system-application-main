package com.dinsaren.hrmanagementsystemapplication.repositories;

import com.dinsaren.hrmanagementsystemapplication.models.Department;
import com.dinsaren.hrmanagementsystemapplication.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findAllByStatus(String status);
    Department findByCode(String code);
}
