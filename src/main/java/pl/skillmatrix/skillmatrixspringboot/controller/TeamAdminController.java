package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.FunctionInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.TeamsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.FunctionInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.TeamsInWarehouseRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix/admin")
@Controller
public class TeamAdminController {
    private final TeamsInWarehouseRepository teamRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;

    @GetMapping("team")
    public String showTeam(Model model) {
        model.addAttribute("teams",teamRepository.findAll());
        return "/skillMatrix/admin/team/team";
    }

    @GetMapping("team/create")
    public String createTeam(Model model, HttpSession httpSession) {
        TeamsInWarehouse team = new TeamsInWarehouse();
        model.addAttribute("team",team);
        return "skillMatrix/admin/team/teamCreate";
    }

    @PostMapping("team/create")
    public String createTeam(@ModelAttribute("team")TeamsInWarehouse team ){
        if(team == null) {
            return "redirect:/skillMatrix/admin/team/create";
        }
        teamRepository.save(team);
        return "redirect:/skillMatrix/admin/team/create";
    }

//    @GetMapping("team/remove/{id}")
//    public String removeTeam(@PathVariable("id")Integer id, Model model) {
//        if(!teamRepository.existsById(id)) {
//            return "redirect:/skillMatrix/admin/team";
//        }
//        model.addAttribute("team", teamRepository.findById(id));
//        return "/skillMatrix/admin/teamRemove";
//    }
//
//    @PostMapping("team/remove/remove")
//    public String removeTeam(@ModelAttribute("id")Integer id) {
//        if(!teamRepository.existsById(id)){
//            return "redirect:/skillMatrix/admin/team";
//        }
//        teamRepository.deleteById(id);
//        return "redirect:/skillMatrix/admin/team";
//    }



    @GetMapping("team/edit/{id}")
    public String modifyTeam(@PathVariable("id")Integer id, Model model) {
        if(!teamRepository.existsById(id)){
            return "redirect:/skillMatrix/admin/team";
        }
        model.addAttribute("team", teamRepository.findById(id));
        return "/skillMatrix/admin/team/teamEdit";
    }

    @PostMapping("team/edit")
    public String modifyTeam(@ModelAttribute("team") TeamsInWarehouse team) {
        teamRepository.save(team);
        return "redirect:/skillMatrix/admin/team";
    }


    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

}
