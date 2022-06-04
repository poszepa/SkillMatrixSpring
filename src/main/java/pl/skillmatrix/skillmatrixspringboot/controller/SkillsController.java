package pl.skillmatrix.skillmatrixspringboot.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.OwnedSkill;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.OwnedSkillRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;
import pl.skillmatrix.skillmatrixspringboot.service.OwnedSkillService;
import pl.skillmatrix.skillmatrixspringboot.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix")
@Controller
public class SkillsController {
    private final PersonRepository personRepository;
    private final PersonService personService;
    private final SkillsRepository skillsRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;
    private final OwnedSkillRepository ownedSkillRepository;
    private final OwnedSkillService ownedSkillService;


    @GetMapping("skills/{department}")
    public String homeSkills(@PathVariable("department") String department, Model model) {
        if(department == null) {
            return "redirect:/skillMatrix/home";
        }
        model.addAttribute("skills", skillsRepository.findSkillsByDepartmentsInWarehouseNameDepartment(department));
        return "/skillMatrix/skills";
    }

    @GetMapping("skills/{department}/{skillName}")
    public String skillWithPeopleList(@PathVariable("department")String department, @PathVariable("skillName") String skillName, Model model){
        if(skillName == null) {
            return "redirect:/skillMatrix/" + department;
        }
        if(department == null) {
            return "redirect:/skillMatrix/home";
        }
        model.addAttribute("ownedskills", ownedSkillRepository.findAllPersonBySkillID(skillsRepository.findSkillByDepartmentNameAndNameSkill(skillName, department).getId()));
        return "/skillMatrix/skillsPersons";
    }

    @PostMapping("skills/{department}/{skillName}")
    public String skillWithPeopleList(HttpServletRequest req, @PathVariable("department")String department,
                                      @PathVariable("skillName")String skillName) {
        ownedSkillService.changeSkilledBoolean(Long.parseLong(req.getParameter("id")));
        return "redirect:/skillMatrix/skills/" + department + "/" + skillName;
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    @ModelAttribute("skills")
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }



}
