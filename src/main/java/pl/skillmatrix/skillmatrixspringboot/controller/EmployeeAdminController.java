package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.Person;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.PersonRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("skillMatrix/admin")
@RequiredArgsConstructor
public class EmployeeAdminController {

    private final PersonRepository personRepository;
    private final DepartmentsInWarehouseRepository departmentRepository;

    @GetMapping("employee")
    public String getListEmployee(Model model) {
        model.addAttribute("employees", personRepository.findAll());
        return "skillMatrix/admin/employee";
    }

    @PostMapping("employee")
    public String getListEmployee(HttpServletRequest request) {
        if(request.getParameter("idEmployee") != null) {
            Integer id = Integer.parseInt(request.getParameter("idEmployee"));
            Person person = personRepository.findPersonById(id);
            if(person.getActive() == false) {
                person.setActive(true);
            }else{
                person.setActive(false);
            }
            personRepository.save(person);
        }

        return "redirect:/skillMatrix/admin/employee";
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getDepartments() {
        return departmentRepository.findAll();
    }
}
