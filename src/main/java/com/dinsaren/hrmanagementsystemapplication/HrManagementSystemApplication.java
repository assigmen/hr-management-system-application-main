package com.dinsaren.hrmanagementsystemapplication;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.*;
import com.dinsaren.hrmanagementsystemapplication.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HrManagementSystemApplication {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder registerPasswordEncoder;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    public HrManagementSystemApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder registerPasswordEncoder, EmployeeRepository employeeRepository, PositionRepository positionRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.registerPasswordEncoder = registerPasswordEncoder;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HrManagementSystemApplication.class, args);
    }

    @PostConstruct
    public void migrationData(){
        var checkUser = userRepository.findByUsername("admin");
        if(checkUser.isEmpty()){
            var checkRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if(checkRoleAdmin.isEmpty()){
                Role role = new Role();
                role.setName("ROLE_ADMIN");
                roleRepository.save(role);
            }
            var checkRoleUser = roleRepository.findByName("ROLE_USER");
            if(checkRoleUser.isEmpty()){
                Role role = new Role();
                role.setName("ROLE_USER");
                roleRepository.save(role);
            }
            User user = new User();
            user.setEmail("chanrithy@gmail.com");
            user.setPhone("096696996");
            user.setStatus(Constant.STATUS_ACTIVE);
            user.setUsername("admin");
            Set<Role> roles = new HashSet<>();
            Role userAdmin = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            roles.add(userAdmin);
            user.setPassword(registerPasswordEncoder.encode("admin@123"));
            user.setRoles(roles);
            userRepository.save(user);
        }
        var position = positionRepository.findAll();
        if(position.isEmpty()){
            var ceo = new Position();
            ceo.setName("CEO Officer");
            ceo.setCode("P001");
            ceo.setStatus(Constant.STATUS_ACTIVE);
            positionRepository.save(ceo);
        }
        var department = departmentRepository.findAll();
        if(department.isEmpty()){
            var hr = new Department();
            hr.setName("Human Resource");
            hr.setCode("D001");
            hr.setStatus(Constant.STATUS_ACTIVE);
            departmentRepository.save(hr);
            var ceo = new Department();
            ceo.setCode("D002");
            ceo.setName("Head Of Management");
            ceo.setStatus(Constant.STATUS_ACTIVE);
            departmentRepository.save(ceo);
            var technology = new Department();
            technology.setCode("D003");
            technology.setName("Information Technology");
            technology.setStatus(Constant.STATUS_ACTIVE);
            departmentRepository.save(technology);
        }

        var employee = employeeRepository.findAllByStatusInOrderByEmployeeNoAsc(Constant.getAllStatusString());
        if(employee.isEmpty()){
            Employee createEmployee = new Employee();
            createEmployee.setGender("Male");
            createEmployee.setFirstName("Din");
            createEmployee.setLastName("Saren");
            createEmployee.setName("Din Saren");
            createEmployee.setEmployeeNo("EMP-1");
            createEmployee.setAddress("Phnom penh");
            createEmployee.setLineManager(null);
            createEmployee.setDepartment(departmentRepository.findByCode("D002"));
            createEmployee.setPosition(positionRepository.findByCode("P001"));
            createEmployee.setPhone("096205045");
            createEmployee.setEmail("dinsarenkh@gmail.com");
            createEmployee.setStatus(Constant.STATUS_ACTIVE);
            createEmployee.setCreatedBy("SYS");
            createEmployee.setCreatedDate(new Date());
            createEmployee.setJoinedDate(new Date());
            employeeRepository.save(createEmployee);
        }

    }

}
