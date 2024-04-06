package com.dinsaren.hrmanagementsystemapplication.controllers;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Position;
import com.dinsaren.hrmanagementsystemapplication.services.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("positions", positionService.getAll());
        return "position/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        Position position = new Position();
        position.setStatusList(Constant.getAllStatus());
        model.addAttribute("object",position);
        return "position/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute("object") Position req){
        System.out.println(req);
        positionService.create(req);
        return "redirect:/positions";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        var find = positionService.getById(id);
        if(find != null) {
            find.setStatusList(Constant.getAllStatus());
            model.addAttribute("object", find);
            return "position/create";
        }
        return "redirect:/positions";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        var find = positionService.getById(id);
        find.setStatus(Constant.STATUS_DELETE);
        positionService.update(find);
        return "redirect:/positions";
    }


}
