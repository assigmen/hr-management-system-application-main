package com.dinsaren.hrmanagementsystemapplication.controllers;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Employee;
import com.dinsaren.hrmanagementsystemapplication.services.DepartmentService;
import com.dinsaren.hrmanagementsystemapplication.services.EmployeeService;
import com.dinsaren.hrmanagementsystemapplication.services.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    @Value("${spring.upload.server.path}")
    private String serverPath;
    @Value("${spring.upload.client.path}")
    private String clintPath;
    private String employeePath = "/employee";
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private static final String DOT = ".";

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, PositionService positionService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.positionService = positionService;
    }

    @GetMapping
    public String index(Model model){
//        model.addAttribute("employees", employeeService.getAllEmployeeAllStatus());
        return "employee/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Employee employee = new Employee();
        employee.setDepartments(departmentService.getAllDepartmentStatusActive());
        employee.setGenders(Constant.getAllGenders());
        employee.setStatusList(Constant.getAllStatus());
        employee.setPositions(positionService.getAllStatusActive());
        employee.setLineManagers(employeeService.getAllEmployeeStatusActive());
        model.addAttribute("employee",employee);
        return "employee/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("employee") Employee employee) throws IOException {

        var emp = employeeService.getEmployeeById(employee.getId());
        String writePath = serverPath + employeePath;
        File path = new File(writePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        String imageUrl = "";
        MultipartFile file = employee.getFile();
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                fileName = UUID.randomUUID() + "." + (fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : null);
                Files.copy(file.getInputStream(), Paths.get(writePath, fileName));
                imageUrl = "/image" + employeePath + "/" + fileName;
                if (emp != null && emp.getProfile() != null) {
                    Path pathDelete = Paths.get(serverPath + emp.getProfile());
                    Files.deleteIfExists(pathDelete);
                }
            } catch (IOException e) {
                log.error("Error upload image :",e);
            }
        }
        if (emp != null) {
            employee.setProfile(emp.getProfile());
            employee.setUpdatedBy("SYS");
            employee.setUpdatedDate(new Date());
        } else {
            employee.setCreatedBy("SYS");
            employee.setCreatedDate(new Date());
        }
        if (!"".equals(imageUrl)) {
            employee.setProfile(imageUrl);
        }
        employeeService.create(employee);
        return "redirect:/employees";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Employee findEmployee = employeeService.getEmployeeById(id);
        if(findEmployee != null) {
            findEmployee.setGenders(Constant.getAllGenders());
            findEmployee.setDepartments(departmentService.getAllDepartmentStatusActive());
            findEmployee.setGenders(Constant.getAllGenders());
            findEmployee.setStatusList(Constant.getAllStatus());
            findEmployee.setPositions(positionService.getAllStatusActive());
            findEmployee.setLineManagers(employeeService.getAllEmployeeStatusActive());
            model.addAttribute("employee", findEmployee);
            return "employee/create";
        }
        return "redirect:/employees";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        employeeService.delete(id);
        return "redirect:/employees";
    }


}
