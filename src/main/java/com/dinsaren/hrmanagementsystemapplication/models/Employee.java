package com.dinsaren.hrmanagementsystemapplication.models;

import com.dinsaren.hrmanagementsystemapplication.models.request.ItemKeyValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hr_employee")
@Setter
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeNo;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    @Column(name = "status", length = 3)
    private String status;
    @Transient
    List<ItemKeyValue> statusList;
    @Transient
    private List<ItemKeyValue> genders;
    @ManyToOne
    private Department department;
    @Transient
    private List<Department> departments;
    @ManyToOne
    private Position position;
    @Transient
    private List<Position> positions;
    @ManyToOne
    @JoinColumn(name = "line_manager_id", referencedColumnName = "id")
    private Employee lineManager;
    @Transient
    private List<Employee> lineManagers;
    private String profile;
    @Transient
    private MultipartFile file;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinedDate;

}
