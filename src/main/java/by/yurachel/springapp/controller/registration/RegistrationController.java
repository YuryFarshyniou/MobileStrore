package by.yurachel.springapp.controller.registration;

import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final String AVATAR_PATH = "C:\\Users\\USER1\\Java Projects\\SpringApp\\src\\main\\resources\\static\\img\\avatar\\";

    private final IService<User> service;
    private final PasswordEncoder encoder;

    public RegistrationController(@Qualifier("userService") IService<User> service,
                                  PasswordEncoder encoder) {
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
                         BindingResult bindingResult,
                         @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }
        if (file.isEmpty()) {
            service.save(user);
            return "redirect:/home";
        }
        user.setHasImage(true);
        try {
            file.transferTo(Paths.get(AVATAR_PATH + user.getUserName() + ".png"));
//            Path path = Paths.get(AVATAR_PATH + user.getUserName() + ".png");
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setPassword(encoder.encode(user.getPassword()));
        service.save(user);
        return "redirect:/home";
    }
}
