package com.dinsaren.hrmanagementsystemapplication.controllers;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Department;
import com.dinsaren.hrmanagementsystemapplication.models.Position;
import com.dinsaren.hrmanagementsystemapplication.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("positions", departmentService.getAllDepartments());
        return "department/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Position position = new Position();
        position.setStatusList(Constant.getAllStatus());
        model.addAttribute("object",position);
        return "department/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("object") Department req){
        System.out.println(req);
        departmentService.create(req);
        return "redirect:/departments";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        var find = departmentService.getById(id);
        if(find != null) {
            find.setStatusList(Constant.getAllStatus());
            model.addAttribute("object", find);
            return "department/create";
        }
        return "redirect:/departments";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        var find = departmentService.getById(id);
        find.setStatus(Constant.STATUS_DELETE);
        departmentService.update(find);
        return "redirect:/departments";
    }


}
