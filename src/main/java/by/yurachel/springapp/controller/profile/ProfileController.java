package by.yurachel.springapp.controller.profile;

import by.yurachel.springapp.config.SecurityUser;
import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import by.yurachel.springapp.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

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
    public String userProfile(@PathVariable Long id, Model model) {
        model.addAttribute("imgUtil", new UserUtils());
        return "profile/profile";
    }

    @GetMapping("/{id}/purchasesList")
    public String purchasesList(@PathVariable Long id, Model model, Authentication authentication) {
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        User user = principal.getUser();
        Order order = user.getPreparatoryOrder();
        List<Phone> userPhones = new ArrayList<>();
        if (order != null) {
            userPhones = order.getPhones();
        }
        Map<String, Map<Phone, Integer>> phones = convertListOfPhonesIntoMap(userPhones);
        model.addAttribute("purchaseList", phones);
        return "profile/purchasesList";
    }

    @GetMapping("/{id}/orders")
    public String ordersList(@PathVariable Long id, Model model, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();

        List<Order> orders = user.getOrdersWithoutPreparatory();
        model.addAttribute("orders", orders);

        return "profile/orders";
    }

    @GetMapping("/{id}/successOrder")
    public String successOrder(@PathVariable Long id) {
        return "fragments/successOrder";
    }

    @GetMapping("/{id}/edit")
    public String editProfile(@PathVariable Long id, Authentication authentication,
                              Model model) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        model.addAttribute("user", user);
        return "profile/editProfile";
    }

    @PostMapping(value = "/{id}")
    public String addPurchaseInPhoneCatalog(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.getPreparatoryOrder();
        if (order == null) {
            order = new Order();
            order.setState(OrderState.PREPARATORY);
            order.setUser(user);
            user.addOrder(order);
        }
        Phone phoneById = phoneService.findById(id);
        order.addPhone(phoneById);


        orderService.save(order);
        return "redirect:/phones";
    }

    @PostMapping(value = "/{id}/plusOp")
    public String addPhoneInPlusOperation(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.getPreparatoryOrder();
        Phone phoneById = phoneService.findById(id);
        order.addPhone(phoneById);

        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/purchasesList";
    }

    @PostMapping(value = "/{id}/order")
    public String makeAnOrder(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.getPreparatoryOrder();
        order.setState(OrderState.ACTIVE);
        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/successOrder";
    }

    @PutMapping(value = "/{id}/orders")
    public String cancelTheOrder(@PathVariable long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.findOrder(id);
        if (!order.getState().toString().equals("CANCELED")) {
            order.setState(OrderState.CANCELED);
        } else {
            order.setState(OrderState.ACTIVE);
        }
        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/orders";
    }

    @PutMapping(value = "/{id}")
    public String updateAvatar(@PathVariable long id, Authentication authentication,
                               @RequestParam("file") MultipartFile file) throws IOException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        user.setAvatar(file.getBytes());
        userService.save(user);
        return "redirect:/profile/" + user.getId();
    }

    @PutMapping(value = "/{id}/editUser")
    public String editUser(@PathVariable long id, Authentication authentication,
                           @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/editProfile";
        }
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User loginUser = securityUser.getUser();
        loginUser.setAddress(user.getAddress());
        loginUser.setEmail(user.getEmail());
        loginUser.setFirstName(user.getFirstName());
        loginUser.setLastName(user.getLastName());
        loginUser.setUserName(user.getUserName());
        userService.save(loginUser);
        return "redirect:/profile/" + user.getId();
    }


    @DeleteMapping(value = "/{id}/minusOperator")
    public String deleteFromMinusOperator(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.getPreparatoryOrder();
        order.deletePhone(id);

        orderService.save(order);
        return "redirect:/profile/" + securityUser.getUser().getId() + "/purchasesList";
    }

    @DeleteMapping(value = "/{id}/allPhones")
    public String removeAllPhonesInOneOrderFromPurchase(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.getPreparatoryOrder();
        order.deleteAllPhones(id);
        if (order.getPhones().isEmpty()) {
            user.getOrders().remove(order);
            orderService.deleteById(order.getId());

            return "redirect:/profile/{id}/purchasesList";
        }
        orderService.save(order);
        return "redirect:/profile/{id}/purchasesList";
    }

    @DeleteMapping(value = "/{id}/order")
    public String deleteOrder(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = user.findOrder(id);
        if (order.getState().toString().equals("CANCELED")) {
            orderService.deleteById(id);
            user.deleteOrder(id);
            return "redirect:/profile/" + user.getId() + "/orders";
        }
        order.setState(OrderState.DELETED);
        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/orders";
    }

    private Map<String, Map<Phone, Integer>> convertListOfPhonesIntoMap(List<Phone> userPhones) {
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
        return phones;
    }
}
