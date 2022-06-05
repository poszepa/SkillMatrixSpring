package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;
import pl.skillmatrix.skillmatrixspringboot.service.SkillsService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

//@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
@Controller
@RequestMapping("/skillMatrix/admin")
public class SkillsAdminController {
    private final DepartmentsInWarehouseRepository departmentsRepository;
    private final SkillsRepository skillsRepository;
    private final PersonRepository personRepository;
    private final SkillsService skillsService;

    @GetMapping("skills")
    public String homeSkills(Model model) {
        model.addAttribute("skills",skillsRepository.findAll());
        return "/skillMatrix/admin/skills";
    }

    @GetMapping("skills/create")
    public String skillsCreate(Model model, HttpSession session) {
        session.setMaxInactiveInterval(1);
        model.addAttribute("skill", new Skills());
        return "/skillMatrix/admin/skillsCreate";
    }

    @PostMapping("skills/create")
    public ModelAndView skillsCreated(@ModelAttribute("skill")@Valid Skills skills, BindingResult result, HttpSession session) {
        if(result.hasErrors()) {
            return new ModelAndView("redirect:/skillMatrix/admin/skills/create");
        }
        skillsService.saveSkill(skills);
        session.setAttribute("successAddSkill", "successAddSkill");
        return new ModelAndView("redirect:/skillMatrix/admin/skills");
    }

    @GetMapping("skills/edit/{id}")
    public String skillEdit(Model model, @PathVariable("id")Integer id, HttpSession session) {
        session.setMaxInactiveInterval(1);
        if(!skillsRepository.existsById(id)){
            return "redirect: /skillMatrix/admin/skills";
        }
        model.addAttribute("skill", skillsRepository.findById(id));
        return "skillMatrix/admin/skillsEdit";
    }

    @PostMapping("skills/edit")
    public String skillEdited(@ModelAttribute("skill") @Valid Skills skills, BindingResult result, HttpSession session) {
        if(result.hasErrors()) {
            return "redirect:/skillMatrix/admin/skills/edit/" + skills.getId();
        }
        skillsService.modifySkills(skills);
        session.setAttribute("successEditSkill", "successEditSkill");
        return "redirect:/skillMatrix/admin/skills/edit/" + skills.getId();
    }

    @GetMapping("skills/remove/{id}")
    public String removeSkill(@PathVariable("id")Integer id,Model model){
        if(!skillsRepository.existsById(id)){
            return "redirect: /skillMatrix/admin/skills";
        }
        model.addAttribute("skill", skillsRepository.findById(id));
        return "/skillMatrix/admin/skillsRemove";
    }

    @PostMapping("/skills/remove")
    public String removedSkill(@ModelAttribute("skill")Skills skills){
            skillsRepository.delete(skills);
        return "redirect:/skillMatrix/admin/skills";
    }


    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }
}
