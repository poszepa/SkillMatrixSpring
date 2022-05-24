package pl.skillmatrix.skillmatrixspringboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.skillmatrix.skillmatrixspringboot.model.User;
import pl.skillmatrix.skillmatrixspringboot.repository.UserRepository;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userService;

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
//        userService.saveUser(user);
        return "admin";
    }
}
