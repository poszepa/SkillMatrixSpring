package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix/admin")
@Controller
public class DashboardController {

    private final DepartmentsInWarehouseRepository departmentsRepository;



    @GetMapping("dashboard")
    public String dashboard() {
        return "/skillMatrix/admin/dashboard";
    }


    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> departmentsList() {
        return departmentsRepository.findAll();
    }
}
