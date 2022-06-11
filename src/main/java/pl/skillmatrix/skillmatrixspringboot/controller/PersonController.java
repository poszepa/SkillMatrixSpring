package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.*;
import pl.skillmatrix.skillmatrixspringboot.repository.*;
import pl.skillmatrix.skillmatrixspringboot.service.ColorService;
import pl.skillmatrix.skillmatrixspringboot.service.OwnedSkillService;
import pl.skillmatrix.skillmatrixspringboot.service.PersonService;

import javax.servlet.http.HttpServletRequest;
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
    private final OwnedSkillService ownedSkillService;
    private final ColorService colorService;

    @GetMapping("person/create")
    public String createPerson(Model model, HttpSession httpSession) {
        model.addAttribute("departmentsWithoutGeneral", departmentsRepository.findDepartmentWithoutGeneral());
        httpSession.getAttribute("successAddPerson");
        model.addAttribute("person", new Person());
        return "skillMatrix/createPerson";
    }

    @PostMapping("person/create")
    public String createPersonPost(@ModelAttribute("person")@Valid Person person, BindingResult result) {
        if(result.hasErrors()){
            return "/skillMatrix/createPerson";
        }
        person.setSkillsList(skillsRepository.findAll());
        personService.savePerson(person);
        return "redirect:/skillMatrix/person";
    }

    @GetMapping("person")
    public String showAllPerson(Model model, HttpSession session) {
        String personDepartment = "";
        String personGroup = "";
        String personTeam = "";

        if(session.getAttribute("everyPeople") != null) {
            session.removeAttribute("departmentPerson");
            session.removeAttribute("groupPerson");
            session.removeAttribute("teamPerson");
            model.addAttribute("allPerson",personRepository.findPersonsByActiveTrue());
            return "/skillMatrix/person";
        }

        if(session.getAttribute("departmentPerson") != null) {
            personDepartment = session.getAttribute("departmentPerson").toString();
        }
        if(session.getAttribute("groupPerson") != null) {
            personGroup = session.getAttribute("groupPerson").toString();
        }
        if(session.getAttribute("teamPerson") != null) {
            personTeam = session.getAttribute("teamPerson").toString();
        }

        model.addAttribute("allPerson",personService.showPerson(personDepartment, personGroup, personTeam));
        return "/skillMatrix/person";
    }

    @PostMapping(value = "person", params = "seachGroup")
    public String redirectToCorrectAddressesWithFilter(HttpServletRequest req, HttpSession session){
        String departmentFromForm = req.getParameter("department");
        String groupFromForm = req.getParameter("group");
        String teamFromForm = req.getParameter("team");

        if(departmentFromForm.equals("everyPeople")) {
            session.setAttribute("everyPeople", departmentFromForm);
            return "redirect:/skillMatrix/person";
        }

        if(!departmentFromForm.isEmpty() && !groupFromForm.isEmpty() && !teamFromForm.isEmpty()) {
            session.setAttribute("departmentPerson", departmentFromForm);
            session.setAttribute("groupPerson", groupFromForm);
            session.setAttribute("teamPerson", teamFromForm);
            session.removeAttribute("everyPeople");
            return "redirect:/skillMatrix/person";
        }
        if(!departmentFromForm.isEmpty() && !groupFromForm.isEmpty()) {
            session.setAttribute("departmentPerson", departmentFromForm);
            session.setAttribute("groupPerson", groupFromForm);
            session.removeAttribute("everyPeople");
            return "redirect:/skillMatrix/person";
        }
        if(!departmentFromForm.isEmpty()){
            session.setAttribute("departmentPerson", departmentFromForm);
            session.removeAttribute("everyPeople");
            return "redirect:/skillMatrix/person";
        }
        return "redirect:/skillMatrix/person";
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
        model.addAttribute("percentSkillsRequired", ownedSkillService.getPercentValueGainedSkillToEveryRequiredSkill(Integer.parseInt(id)));
        model.addAttribute("percentSkills", ownedSkillService.getPercentValueGainedSkillToEverySkill(Integer.parseInt(id)));
        model.addAttribute("color", colorService.colorsToChartList(0.0));
        model.addAttribute("colorBackground", colorService.colorsToChartList(0.2));

        return "skillMatrix/personDescription";
    }

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
