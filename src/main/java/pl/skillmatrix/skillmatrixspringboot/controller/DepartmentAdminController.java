package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix/admin")
@Controller
public class DepartmentAdminController {
    private final DepartmentsInWarehouseRepository departmentsRepository;

    @GetMapping("departments")
    public String showDepartments(Model model) {
        model.addAttribute("departments",departmentsRepository.findAll());
        return "/skillMatrix/admin/department/department";
    }

    @GetMapping("departments/create")
    public String createDepartment(Model model, HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(1);
        DepartmentsInWarehouse department = new DepartmentsInWarehouse();
        model.addAttribute("department",department);
        return "skillMatrix/admin/department/departmentCreate";
    }

    @PostMapping("departments/create")
    public String createdDepartment(@ModelAttribute("department")DepartmentsInWarehouse departments, HttpSession httpSession){
        if(departments == null) {
            return "redirect:/skillMatrix/admin/departments/create";
        }
        departmentsRepository.save(departments);
        httpSession.setAttribute("success", "Correctly added a new department");
        return "redirect:/skillMatrix/admin/departments/create";
    }

//    @GetMapping("departments/remove/{id}")
//    public String removeDepartment(@PathVariable("id")Integer id, Model model) {
//        if(!departmentsRepository.existsById(id)) {
//            return "redirect:/skillMatrix/admin/departments";
//        }
//        model.addAttribute("department", departmentsRepository.findById(id));
//        return "/skillMatrix/admin/departmentRemove";
//    }
//
//    @PostMapping("departments/remove/remove")
//    public String removedDepartment(@ModelAttribute("id")Integer id) {
//        if(!departmentsRepository.existsById(id)){
//            return "redirect:/skillMatrix/admin/departments";
//        }
//        departmentsRepository.deleteById(id);
//        return "redirect:/skillMatrix/admin/departments";
//    }



    @GetMapping("departments/edit/{id}")
    public String modifyDepartmentSite(@PathVariable("id")Integer id, Model model) {
        if(!departmentsRepository.existsById(id)){
            return "redirect:/skillMatrix/admin/departments";
        }
        model.addAttribute("department", departmentsRepository.findById(id));
        return "/skillMatrix/admin/department/departmentEdit";
    }

    @PostMapping("departments/edit")
    public String modifyDepartments(@ModelAttribute("department")DepartmentsInWarehouse departments) {
        departmentsRepository.save(departments);
        return "redirect:/skillMatrix/admin/departments";
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

}
