package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.GroupsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.GroupsInWarehouseRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix/admin")
@Controller
public class GroupAdminController {
    private final GroupsInWarehouseRepository groupRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;

    @GetMapping("group")
    public String showGroups(Model model) {
        model.addAttribute("groups",groupRepository.findAll());
        return "/skillMatrix/admin/group";
    }

    @GetMapping("group/create")
    public String createGroup(Model model, HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(1);
        GroupsInWarehouse group = new GroupsInWarehouse();
        model.addAttribute("group",group);
        return "skillMatrix/admin/groupCreate";
    }

    @PostMapping("group/create")
    public String createGroup(@ModelAttribute("group")@Valid GroupsInWarehouse group , BindingResult bindingResult, HttpSession httpSession){
        if(bindingResult.hasErrors()) {
            return "redirect:/skillMatrix/admin/group/create";
        }
        if(group == null) {
            return "redirect:/skillMatrix/admin/group/create";
        }
        groupRepository.save(group);
        httpSession.setAttribute("success", "Correctly added a new group");
        return "redirect:/skillMatrix/admin/group/create";
    }

//    @GetMapping("group/remove/{id}")
//    public String removeGroup(@PathVariable("id")Integer id, Model model) {
//        if(!groupRepository.existsById(id)) {
//            return "redirect:/skillMatrix/admin/group";
//        }
//        model.addAttribute("group", groupRepository.findById(id));
//        return "/skillMatrix/admin/groupRemove";
//    }
//
//    @PostMapping("group/remove/remove")
//    public String removeGroup(@ModelAttribute("id")Integer id) {
//        if(!groupRepository.existsById(id)){
//            return "redirect:/skillMatrix/admin/group";
//        }
//        groupRepository.deleteById(id);
//        return "redirect:/skillMatrix/admin/group";
//    }



    @GetMapping("group/edit/{id}")
    public String modifyGroup(@PathVariable("id")Integer id, Model model) {
        if(!groupRepository.existsById(id)){
            return "redirect:/skillMatrix/admin/group";
        }
        model.addAttribute("group", groupRepository.findById(id));
        return "/skillMatrix/admin/groupEdit";
    }

    @PostMapping("group/edit")
    public String modifyGroup(@ModelAttribute("group") GroupsInWarehouse group) {
        groupRepository.save(group);
        return "redirect:/skillMatrix/admin/group";
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

}
