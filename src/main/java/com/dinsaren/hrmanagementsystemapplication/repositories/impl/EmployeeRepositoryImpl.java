package com.dinsaren.hrmanagementsystemapplication.repositories.impl;//package com.dinsaren.firstspringbootwebapplication.repositories.impl;
//
//import com.dinsaren.firstspringbootwebapplication.models.Employee;
//import com.dinsaren.firstspringbootwebapplication.repositories.EmployeeRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class EmployeeRepositoryImpl implements EmployeeRepository {
//    private List<Employee> employees = new ArrayList<>();
//
//    public EmployeeRepositoryImpl() {
//        if(employees.isEmpty()){
//            for(int i=1; i<20; i++){
//                Employee employee = new Employee();
//                employee.setId(i);
//                employee.setName("Employee Name : "+i);
//                if(i%2==0){
//                    employee.setGender("Male");
//                }else{
//                    employee.setGender("Female");
//                }
//                employees.add(employee);
//            }
//        }
//    }
//
//    @Override
//    public List<Employee> finAll() {
//        return employees;
//    }
//
//    @Override
//    public void save(Employee employee) {
//        if(employee.getId() == 0){
//            employee.setId(employees.size()+1);
//            employees.add(employee);
//        }else{
//            for(int i=0;i<employees.size(); i++){
//                if(employee.getId()==employees.get(i).getId()){
//                    employees.remove(employees.get(i));
//                    employees.add(i, employee);
//                    break;
//                }
//            }
//        }
//    }
//
//    @Override
//    public Employee findById(Integer id) {
//        for(Employee employee: employees){
//            if(employee.getId()==id){
//                return employee;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void delete(Integer id) {
//        for(int i=0;i<employees.size(); i++){
//            if(id==employees.get(i).getId()){
//                employees.remove(employees.get(i));
//                break;
//            }
//        }
//    }
//}
