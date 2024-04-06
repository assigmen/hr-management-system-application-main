package com.dinsaren.hrmanagementsystemapplication.controllers;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Position;
import com.dinsaren.hrmanagementsystemapplication.models.Role;
import com.dinsaren.hrmanagementsystemapplication.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("roles", roleRepository.findAll());
        return "role/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Position position = new Position();
        position.setStatusList(Constant.getAllStatus());
        model.addAttribute("object",position);
        return "position/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("object") Role req){
        System.out.println(req);
        roleRepository.save(req);
        return "redirect:/roles";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        var find = roleRepository.findById(id).orElse(null);
        if(find != null) {
            model.addAttribute("object", find);
            return "role/create";
        }
        return "redirect:/roles";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        var find = roleRepository.findById(id).orElse(null);
        if(find != null) {
            roleRepository.save(find);
        }
        return "redirect:/roles";
    }


}
