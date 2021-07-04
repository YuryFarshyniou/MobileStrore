package by.yurachel.springapp.controller.home;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final IService<Phone> service;

    public HomeController(IService<Phone> service) {
        this.service = service;
    }

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("users", service.findAll());
        return "home/home";
    }
}
