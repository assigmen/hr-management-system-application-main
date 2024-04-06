package com.dinsaren.hrmanagementsystemapplication.repositories;

import com.dinsaren.hrmanagementsystemapplication.models.Employee;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeRepository extends DataTablesRepository<Employee, Integer> {
    List<Employee> findAllByStatusOrderByIdDesc(String status);
    List<Employee> findAllByStatusInOrderByEmployeeNoAsc(List<String> status);
    Employee findFirstByOrderByIdDesc();
    Employee findByIdAndStatusIn(Long id, List<String> status);

}
