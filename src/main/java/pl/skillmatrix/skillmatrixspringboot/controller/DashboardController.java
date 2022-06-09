package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.SkillsRepository;
import pl.skillmatrix.skillmatrixspringboot.service.OwnedSkillService;
import pl.skillmatrix.skillmatrixspringboot.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix/admin")
@Controller
public class DashboardController {

    private final DepartmentsInWarehouseRepository departmentsRepository;
    private final PersonService personService;
    private final OwnedSkillService ownedSkillService;



    @GetMapping("dashboard")
    public String dashboard(Model model) {
        model.addAttribute("personsCount", personService.countPeopleByDepartmentID());
        model.addAttribute("department", new DepartmentsInWarehouse());
        return "/skillMatrix/admin/dashboard";
    }

    @GetMapping("dashboard/{department}")
    public String dashboard(Model model, @PathVariable("department")String department) {
        model.addAttribute("personsCount", personService.countPeopleByDepartmentID());
        model.addAttribute("department", new DepartmentsInWarehouse());
        model.addAttribute("departmentFromPath", department);

        List<DepartmentsInWarehouse> departmentsInWarehouses = departmentsRepository.findDepartmentWithoutGeneral();
        DepartmentsInWarehouse skillDepartment = departmentsRepository.findByNameDepartment(department);

        List<Integer> countPeopleWithAllSkillFromChoosedDepartment = new ArrayList<>();

        departmentsInWarehouses.forEach(departments -> countPeopleWithAllSkillFromChoosedDepartment.add(
                ownedSkillService.getCountEmployeeWhoGotFullSkillsFromDepartment(
                        departments.getId(), skillDepartment.getId()
                )));

        model.addAttribute("personCountFromDepartment", countPeopleWithAllSkillFromChoosedDepartment);


        return "/skillMatrix/admin/dashboard";
    }

    @PostMapping("dashboard")
    public String dashboard(@ModelAttribute("department")DepartmentsInWarehouse departments) {
        return "redirect:/skillMatrix/admin/dashboard/" + departments.getNameDepartment();
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> departmentsList() {
        return departmentsRepository.findAll();
    }


}
