package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.*;
import pl.skillmatrix.skillmatrixspringboot.repository.*;
import pl.skillmatrix.skillmatrixspringboot.service.PersonService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("skillMatrix")
public class PersonController {
    private final PersonRepository personRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;
    private final FunctionInWarehouseRepository functionRepository;
    private final GroupsInWarehouseRepository groupsRepository;
    private final TeamsInWarehouseRepository teamRepository;
    private final SkillsRepository skillsRepository;
    private final PersonService personService;

    @GetMapping("person/create")
    public String createPerson(Model model, HttpSession httpSession) {
        httpSession.getAttribute("successAddPerson");
        httpSession.setMaxInactiveInterval(1);
        model.addAttribute("person", new Person());
        return "skillMatrix/createPerson";
    }

    @PostMapping("person/create")
    public String createPersonPost(@ModelAttribute("Person") @Valid Person person, BindingResult result, HttpSession httpSession) {
        if(result.hasErrors()){
            return "redirect:/skillMatrix/person/create";
        }
        person.setSkillsList(skillsRepository.findAll());
        personService.savePerson(person);
        httpSession.setAttribute("successAddPerson", person);
        return "redirect:/skillMatrix/person";
    }

    @GetMapping("person")
    public String showAllPerson(Model model) {
        model.addAttribute("allPerson", personRepository.findPersonByActiveTrue());
        return "skillMatrix/person";
    }

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("skills", skillsRepository.skillsLatestTenUpdatedSkills());
        return "/skillMatrix/homePageAfterLogin";
    }

    @GetMapping("/person/{id}")
    public String descriptionPerson(@PathVariable("id") String id, Model model) {
        Optional<Person> person = personRepository.findById(Integer.parseInt(id));
        model.addAttribute("person", person);
        return "skillMatrix/personDescription";
    }

    @Transactional
    @PostMapping("/person/edit")
    public String modifyPerson(@ModelAttribute("person")@Valid Person person, BindingResult result) {
        if(result.hasErrors()) {
            return "redirect:/skillMatrix/person/" +person.getId();
        }
        personRepository.save(person);

        return "redirect:/skillMatrix/person/" + person.getId();
    }

    @GetMapping("/person/remove/{id}")
    public String alertRemovePerson(@PathVariable("id") String id, Model model) {
        if(!personRepository.existsById(Integer.parseInt(id))) {
            return "redirect:/skillMatrix/person";
        }
        model.addAttribute("person", personRepository.findById(Integer.parseInt(id)));
        return "/skillMatrix/personRemove";
    }

    @Transactional
    @PostMapping("/person/remove")
    public String removePerson(@ModelAttribute("person")Person person) {
        if(!personRepository.existsById(person.getId())) {
            return "redirect:/skillMatrix/person/" + person.getId();
        }
        Person personFromDB = personRepository.findPersonById(person.getId());
        personFromDB.setActive(false);
        personRepository.save(personFromDB);
        return "redirect:/skillMatrix/person";
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    @ModelAttribute("functions")
    public List<FunctionInWarehouse> getAllFunctions() {
        return functionRepository.findAll();
    }

    @ModelAttribute("groups")
    public List<GroupsInWarehouse> getAllGroups() {
        return groupsRepository.findAll();
    }

    @ModelAttribute("teams")
    public List<TeamsInWarehouse> getAllTeams() {
        return teamRepository.findAll();
    }
}
