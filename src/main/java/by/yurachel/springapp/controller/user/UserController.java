package by.yurachel.springapp.controller.user;

import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final IService<User> service;

    public UserController(@Qualifier("userService") IService<User> service) {
        this.service = service;
    }

    @GetMapping()
    public String phoneList(Model model) {
        model.addAttribute("users", service.findAll());
        return "users/users";
    }
}
