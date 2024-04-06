package com.dinsaren.hrmanagementsystemapplication.services.impl;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Department;
import com.dinsaren.hrmanagementsystemapplication.models.Position;
import com.dinsaren.hrmanagementsystemapplication.repositories.DepartmentRepository;
import com.dinsaren.hrmanagementsystemapplication.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> getAllDepartmentStatusActive() {
        return departmentRepository.findAllByStatus(Constant.STATUS_ACTIVE);
    }

    @Override
    public Department getById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Department req) {
        var find = departmentRepository.findById(req.getId()).orElse(null);
        if(find != null){
            var code = departmentRepository.findByCode(req.getCode());
            if(code != null && find.getCode().equals(code.getCode())){
                departmentRepository.save(req);
            }else{
                departmentRepository.save(req);
            }

        }
    }

    @Override
    public void create(Department req) {
        var code = departmentRepository.findByCode(req.getCode());
        if(code == null){
            departmentRepository.save(req);
        }

    }

}
