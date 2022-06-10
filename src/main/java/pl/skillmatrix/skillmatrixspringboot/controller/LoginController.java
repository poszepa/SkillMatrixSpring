package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.skillmatrix.skillmatrixspringboot.model.User;
import pl.skillmatrix.skillmatrixspringboot.service.AuthService;
import pl.skillmatrix.skillmatrixspringboot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user , BindingResult bindingResult, HttpServletRequest req) {
        if(bindingResult.hasErrors()) {
            return "redirect:/register";
        }
        String rePassword = req.getParameter("passwordControllRe");
        if(!user.getPassword().equals(rePassword)) {
            return "redirect:/register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }
}
