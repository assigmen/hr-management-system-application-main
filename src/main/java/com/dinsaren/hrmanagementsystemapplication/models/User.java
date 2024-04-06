package com.dinsaren.hrmanagementsystemapplication.models;

import com.dinsaren.hrmanagementsystemapplication.models.request.ItemKeyValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "hr_user")
@Getter
@Setter
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String phone;
    private String profile;
    private String email;
    private String password;
    private String status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hr_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Transient
    private MultipartFile file;
    @Transient
    List<ItemKeyValue> statusList;
}
