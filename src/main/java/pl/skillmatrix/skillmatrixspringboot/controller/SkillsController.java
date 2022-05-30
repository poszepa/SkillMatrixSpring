package pl.skillmatrix.skillmatrixspringboot.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;
import pl.skillmatrix.skillmatrixspringboot.service.PersonService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix")
@Controller
public class SkillsController {
    private final PersonRepository personRepository;
    private final PersonService personService;
    private final SkillsRepository skillsRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;


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
        model.addAttribute("persons", personService.findPersonBySkillNameAndDepartmentName(skillName, department));
        return "/skillMatrix/skillsPersons";
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
