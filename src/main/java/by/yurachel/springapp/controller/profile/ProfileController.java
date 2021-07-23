package by.yurachel.springapp.controller.profile;

import by.yurachel.springapp.config.SecurityUser;
import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final IService<Phone> phoneService;
    private final IService<User> userService;
    private final IService<Order> orderService;
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ProfileController(IService<Phone> phoneService, IService<User> userService, IService<Order> orderService) {
        this.phoneService = phoneService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id) {
        return "profile/profile";
    }

    @GetMapping("/{id}/purchasesList")
    public String purchasesList(@PathVariable Long id, Model model, Authentication authentication) {

        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        List<Phone> userPhones = principal.getUser().getPhones();
        Map<String, Map<Phone, Integer>> phones = convertListIntoMap(userPhones);
        model.addAttribute("purchaseList", phones);

        return "profile/purchasesList";
    }

    @GetMapping("/{id}/orders")
    public String ordersList(@PathVariable Long id, Authentication authentication) {
        return "profile/orders";
    }

    @PostMapping(value = "/{id}", name = "addPurchaseInPhoneCatalog")
    public String addPurchaseInPhoneCatalog(@PathVariable Long id, Authentication authentication) {
        Phone phoneById = phoneService.findById(id);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        securityUser.getUser().addPhone(phoneById);
        phoneById.addUser(securityUser.getUser());

        userService.save(securityUser.getUser());
        return "redirect:/phones";
    }

    @PostMapping(value = "/{id}/plusOp", name = "addPurchaseInPlusOperation")
    public String addPhoneInPlusOperation(@PathVariable Long id, Authentication authentication) {
        Phone phoneById = phoneService.findById(id);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        securityUser.getUser().addPhone(phoneById);
//        phoneById.addUser(securityUser.getUser());

        userService.save(securityUser.getUser());
        return "redirect:/profile/" + securityUser.getUser().getId() + "/purchasesList";
    }

    @PostMapping(value = "/{id}/order", name = "makeAnOrder")
    public String makeAnOrder(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = new Order();
//        order.setPhones(user.getPhones());
//        order.setUser(user);

        user.getPhones().clear();

        userService.save(user);
//        System.out.println(user.getOrders());
        return "redirect:/profile/" + user.getId() + "/purchasesList";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteFromPurchase(@PathVariable Long id, Authentication authentication) {
        phoneService.deleteById(id);

        return "redirect:/phones";
    }

    @DeleteMapping(value = "/{id}/minusOperator")
    public String deleteFromMinusOperator(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        user.deletePhone(id);
        Phone phone = phoneService.findById(id);

        userService.save(user);
        return "redirect:/profile/" + securityUser.getUser().getId() + "/purchasesList";
    }

    @DeleteMapping(value = "/{id}/allPhones", name = "removeAllPhonesInOneOrderFromPurchase")
    public String removeAllPhonesInOneOrderFromPurchase(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        user.deleteAllPhones(id);
        Phone phone = phoneService.findById(id);
        phone.deleteAllUsers(user.getId());

        userService.save(user);
        return "redirect:/profile/{id}/purchasesList";
    }

    private Map<String, Map<Phone, Integer>> convertListIntoMap(List<Phone> userPhones) {
        System.out.println("UserPhones: " + userPhones);
        Map<String, Map<Phone, Integer>> phones = new HashMap<>();
        for (Phone phone : userPhones) {
            Map<Phone, Integer> s = new HashMap<>();
            if (phones.containsKey(phone.getName())) {
                s = phones.get(phone.getName());
                Set<Phone> set = s.keySet();
                Phone p = (Phone) set.toArray()[0];
                int i = s.get(p) + 1;
                s.put(p, i);
            } else {
                s.put(phone, 1);
            }
            phones.put(phone.getName(), s);
        }
        System.out.println("MAP : " + phones);
        return phones;
    }
}
