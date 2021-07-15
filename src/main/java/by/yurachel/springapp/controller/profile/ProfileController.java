package by.yurachel.springapp.controller.profile;

import by.yurachel.springapp.config.SecurityUser;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.MalformedURLException;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final IService<Phone> phoneService;
    private final IService<User> userService;
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ProfileController(IService<Phone> phoneService, IService<User> userService) {
        this.phoneService = phoneService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id) {
        return "profile/profile";
    }

    @GetMapping("/{id}/purchasesList")
    public String purchasesList(@PathVariable Long id, Model model, Authentication authentication){
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        model.addAttribute("purchaseList", principal.getUser().getPhones());

        return "profile/purchasesList";
    }

    @PostMapping(value = "/{id}", name = "addPurchase")
    public String addPurchase(@PathVariable Long id, Authentication authentication) {
        Phone phoneById = phoneService.findById(id);

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        securityUser.getUser().addPhone(phoneById);
        phoneById.addUser(securityUser.getUser());

        userService.save(securityUser.getUser());
        phoneService.save(phoneById);
        return "redirect:/phones";
    }

    @DeleteMapping(value = "/{id}", name = "removeFromPurchase")
    public String deleteFromPurchase(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        user.deletePhone(id);
        Phone phone = phoneService.findById(id);
        phone.deleteUser(user.getId());

        userService.save(user);
        phoneService.save(phone);
        return "redirect:/profile/{id}/purchasesList";
    }


}
