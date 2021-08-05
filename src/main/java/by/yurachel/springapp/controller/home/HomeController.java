package by.yurachel.springapp.controller.home;


import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final IService<Phone> phoneService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(IService<Phone> phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping()
    public String home(Model model) {
        List<Phone> carouselItemActive = new ArrayList<>();
        carouselItemActive.add(phoneService.findById(1L));
        carouselItemActive.add(phoneService.findById(2L));
        carouselItemActive.add(phoneService.findById(3L));
        carouselItemActive.add(phoneService.findById(4L));
        List<Phone> carouselItem = new ArrayList<>();
        carouselItem.add(phoneService.findById(5L));
        carouselItem.add(phoneService.findById(6L));
        carouselItem.add(phoneService.findById(7L));
        carouselItem.add(phoneService.findById(8L));

        model.addAttribute("itemActive", carouselItemActive);
        model.addAttribute("item", carouselItem);
        return "home/home";
    }

}
