package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skillmatrix.skillmatrixspringboot.model.DepartmentsInWarehouse;
import pl.skillmatrix.skillmatrixspringboot.repository.DepartmentsInWarehouseRepository;
import pl.skillmatrix.skillmatrixspringboot.repository.UserRepository;
import pl.skillmatrix.skillmatrixspringboot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/skillMatrix/admin")
@Controller
@RequiredArgsConstructor
public class UserConfigureController {
    private final UserRepository userRepository;
    private final DepartmentsInWarehouseRepository departmentsRepository;
    private final UserService userService;

    @GetMapping("/usersConfigure")
    public String usersConfigure(Model model) {
        model.addAttribute("admin", userRepository.findAll());
        model.addAttribute("area", userRepository.findAllByRole("ROLE_User"));

        return "/skillMatrix/admin/usersController";
    }

    @PostMapping("/usersConfigure")
    public String usersConfigure(HttpServletRequest req) {
        Integer userID = Integer.parseInt(req.getParameter("idUser"));
        userService.modifyEnabledUser(userID);
        return "redirect:/skillMatrix/admin/usersConfigure";
    }

    @PostMapping(value = "/usersConfigure/role")
    public String usersConfigureRole(HttpServletRequest req) {
        Integer userID = Integer.parseInt(req.getParameter("idUser"));
        String role = req.getParameter("role");
        userService.modifyRoleUser(userID,role);
        return "redirect:/skillMatrix/admin/usersConfigure";
    }

    @ModelAttribute("departments")
    public List<DepartmentsInWarehouse> getAllDepartments() {
        return departmentsRepository.findAll();
    }

}
