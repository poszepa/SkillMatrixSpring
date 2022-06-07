package pl.skillmatrix.skillmatrixspringboot.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.*;
import pl.skillmatrix.skillmatrixspringboot.repository.*;
import pl.skillmatrix.skillmatrixspringboot.service.OwnedSkillService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix")
@Controller
public class SkillsController {
    private final SkillsRepository skillsRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;
    private final OwnedSkillService ownedSkillService;
    private final TeamsInWarehouseRepository teamRepository;
    private final GroupsInWarehouseRepository groupRepository;



    @GetMapping("skills/{department}")
    public String homeSkills(@PathVariable("department") String department, Model model) {
        if(department == null) {
            return "redirect:/skillMatrix/home";
        }
        model.addAttribute("skills", skillsRepository.findSkillsByDepartmentsInWarehouseNameDepartment(department));
        return "/skillMatrix/skills";
    }

    @GetMapping("skills/{department}/{skillName}")
    public String skillWithPeopleList(@PathVariable("department")String department, @PathVariable("skillName") String skillName, Model model, HttpSession session){
        String personDepartment = "";
        String personGroup = "";
        String personTeam = "";
        model.addAttribute("departmentName", department);
        model.addAttribute("skillName", skillName);

        if(session.getAttribute("everyPeople") != null) {

            session.removeAttribute("departmentPerson");
            session.removeAttribute("groupPerson");
            session.removeAttribute("teamPerson");
            model.addAttribute("ownedskills",ownedSkillService.showEveryPersonWithSkill(skillName, department));
            return "/skillMatrix/skillsPersons";
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


        model.addAttribute("ownedskills",ownedSkillService.showPersonWithSkill(personDepartment, personGroup, personTeam, skillName, department));
        return "/skillMatrix/skillsPersons";
    }

    @PostMapping("skills/{department}/{skillName}")
    public String skillWithPeopleList(HttpServletRequest req, @PathVariable("department")String department,
                                      @PathVariable("skillName")String skillName) {
        ownedSkillService.changeSkilledBoolean(Long.parseLong(req.getParameter("id")));
        return "redirect:/skillMatrix/skills/" + department + "/" + skillName;
    }

    @PostMapping(value = "skills/{department}/{skillName}", params = "seachGroup")
    public String redirectToCorrectAddressesWithFilter(HttpServletRequest req, @PathVariable("department")String department, @PathVariable("skillName")String skillName, HttpSession session){
        String departmentFromForm = req.getParameter("department");
        String groupFromForm = req.getParameter("group");
        String teamFromForm = req.getParameter("team");

        if(departmentFromForm.equals("everyPeople")) {
            session.setAttribute("everyPeople", departmentFromForm);
            return "redirect:/skillMatrix/skills/" + department + "/" + skillName;
        }

        if(!departmentFromForm.isEmpty() && !groupFromForm.isEmpty() && !teamFromForm.isEmpty()) {
            session.setAttribute("departmentPerson", departmentFromForm);
            session.setAttribute("groupPerson", groupFromForm);
            session.setAttribute("teamPerson", teamFromForm);
            session.removeAttribute("everyPeople");
            return "redirect:/skillMatrix/skills/" + department + "/" + skillName;
        }
        if(!departmentFromForm.isEmpty() && !groupFromForm.isEmpty()) {
            session.setAttribute("departmentPerson", departmentFromForm);
            session.setAttribute("groupPerson", groupFromForm);
            session.removeAttribute("everyPeople");
            return "redirect:/skillMatrix/skills/" + department + "/" + skillName;
        }
        if(!departmentFromForm.isEmpty()){
            session.setAttribute("departmentPerson", departmentFromForm);
            session.removeAttribute("everyPeople");
            return "redirect:/skillMatrix/skills/" + department + "/" + skillName;
        }
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
    @ModelAttribute("teams")
    public List<TeamsInWarehouse> getAllTeam() {
        return teamRepository.findAll();
    }

    @ModelAttribute("groups")
    public List<GroupsInWarehouse> getAllGroup() {
        return groupRepository.findAll();
    }



}
