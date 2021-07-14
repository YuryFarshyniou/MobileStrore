package by.yurachel.springapp.controller.profile;

import by.yurachel.springapp.config.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {


    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id) {

        return "profile/profile";
    }

    @GetMapping("/{id}/purchasesList")
    public String purchasesList(@PathVariable Long id, Model model, Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        model.addAttribute("purchaseList", user.getUser().getPhones());
        return "profile/purchasesList";
    }

}
