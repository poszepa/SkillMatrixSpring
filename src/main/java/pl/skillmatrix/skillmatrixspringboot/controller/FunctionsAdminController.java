package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.FunctionInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.model.Skills;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.FunctionInWarehouseRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("skillMatrix/admin")
@Controller
public class FunctionsAdminController {
    private final FunctionInWarehouseRepository functionRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;

    @GetMapping("function")
    public String showFunctions(Model model) {
        model.addAttribute("functions",functionRepository.findAll());
        return "/skillMatrix/admin/function/function";
    }

    @GetMapping("function/create")
    public String createFunction(Model model, HttpSession httpSession) {
        httpSession.setMaxInactiveInterval(1);
        FunctionInWarehouse function = new FunctionInWarehouse();
        model.addAttribute("function",function);
        return "skillMatrix/admin/function/functionCreate";
    }

    @PostMapping("function/create")
    public String createFunction(@ModelAttribute("function")@Valid FunctionInWarehouse function, BindingResult bindingResult, HttpSession httpSession){
        if(bindingResult.hasErrors()) {
            return "redirect:/skillMatrix/admin/function/create";
        }
        if(function == null) {
            return "redirect:/skillMatrix/admin/function/create";
        }
        functionRepository.save(function);
        httpSession.setAttribute("success", "Correctly added a new function");
        return "redirect:/skillMatrix/admin/function/create";
    }

//    @GetMapping("function/remove/{id}")
//    public String removeFunction(@PathVariable("id")Integer id, Model model) {
//        if(!functionRepository.existsById(id)) {
//            return "redirect:/skillMatrix/admin/function";
//        }
//        model.addAttribute("function", functionRepository.findById(id));
//        return "/skillMatrix/admin/functionRemove";
//    }
//
//    @PostMapping("function/remove/remove")
//    public String removeFunction(@ModelAttribute("id")Integer id) {
//        if(!functionRepository.existsById(id)){
//            return "redirect:/skillMatrix/admin/function";
//        }
//        functionRepository.deleteById(id);
//        return "redirect:/skillMatrix/admin/function";
//    }



    @GetMapping("function/edit/{id}")
    public String modifyFunction(@PathVariable("id")Integer id, Model model) {
        if(!functionRepository.existsById(id)){
            return "redirect:/skillMatrix/admin/function";
        }
        model.addAttribute("function", functionRepository.findById(id));
        return "/skillMatrix/admin/function/functionEdit";
    }

    @PostMapping("function/edit")
    public String modifyFunction(@ModelAttribute("function") FunctionInWarehouse function) {
        functionRepository.save(function);
        return "redirect:/skillMatrix/admin/function";
    }

    @ModelAttribute("functions")
    public List<FunctionInWarehouse> getAllFunction() {
        return functionRepository.findAll();
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

}
