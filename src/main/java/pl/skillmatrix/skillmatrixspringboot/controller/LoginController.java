package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.skillmatrix.skillmatrixspringboot.model.User;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "home";
    }
}
