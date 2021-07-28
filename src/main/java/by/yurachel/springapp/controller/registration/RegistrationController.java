package by.yurachel.springapp.controller.registration;

import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final IService<User> service;
    private final PasswordEncoder encoder;

    public RegistrationController(IService<User> service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping("")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration/registration";
    }

    @PostMapping
    public String create(@ModelAttribute("newUser") @Valid User user,
                         BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRegistrationDate(new Date());
        service.save(user);
        return "redirect:/home";
    }
}
