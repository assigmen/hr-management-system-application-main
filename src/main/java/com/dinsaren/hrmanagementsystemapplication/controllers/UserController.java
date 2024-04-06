package com.dinsaren.hrmanagementsystemapplication.controllers;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Employee;
import com.dinsaren.hrmanagementsystemapplication.models.Role;
import com.dinsaren.hrmanagementsystemapplication.models.User;
import com.dinsaren.hrmanagementsystemapplication.repositories.RoleRepository;
import com.dinsaren.hrmanagementsystemapplication.repositories.UserRepository;
import com.dinsaren.hrmanagementsystemapplication.services.DepartmentService;
import com.dinsaren.hrmanagementsystemapplication.services.EmployeeService;
import com.dinsaren.hrmanagementsystemapplication.services.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Value("${spring.upload.server.path}")
    private String serverPath;
    @Value("${spring.upload.client.path}")
    private String clintPath;
    private String user = "/user";
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder registerPasswordEncoder;

    private static final String DOT = ".";


    @GetMapping
    public String index(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        User user = new User();
        user.setStatusList(Constant.getAllStatus());
        model.addAttribute("user",user);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "user/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) throws IOException {
        String writePath = serverPath + this.user;
        File path = new File(writePath);
        if (!path.exists()) {
            if (path.mkdirs()) {
            } else {
            }
        }
        MultipartFile file = user.getFile();
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                fileName = UUID.randomUUID() + "." + (fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : null);
                Files.copy(file.getInputStream(), Paths.get(writePath, fileName));
                user.setProfile("/image" + this.user + "/" + fileName);
            } catch (IOException e) {

            }
        }
        if(user.getId() == 0) {
            user.setPassword(registerPasswordEncoder.encode(user.getPassword()));
        }else{
            var userFind =userRepository.findById(user.getId()).orElse(null);
            if(userFind != null) {
                if (!"".equals(user.getPassword())) {
                    user.setPassword(registerPasswordEncoder.encode(user.getPassword()));
                } else {
                    user.setPassword(userFind.getPassword());
                }
            }
        }
        Set<Role> roleList = new HashSet<>();
        if(!user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                roleList.add(role);
            }
        }
        user.setRoles(roleList);
        userRepository.save(user);
        return "redirect:/users";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setPassword("");
            user.setStatusList(Constant.getAllStatus());
            model.addAttribute("user", user);
            model.addAttribute("allRoles", roleRepository.findAll());
            return "user/create";
        }
        return "redirect:/users";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        var user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setStatus(Constant.STATUS_DELETE);
            userRepository.save(user);
        }
        return "redirect:/users";
    }


}
