package by.yurachel.springapp.controller.profile;

import by.yurachel.springapp.config.SecurityUser;
import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.service.IService;
import by.yurachel.springapp.util.orderUtils.OrderUtilsInt;
import by.yurachel.springapp.util.userUtils.impl.UserUtilsInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final UserUtilsInt userUtilsInt;
    private final OrderUtilsInt orderUtils;

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);


    public ProfileController(IService<Phone> phoneService,
                             IService<User> userService,
                             IService<Order> orderService,
                             @Qualifier(value = "userUtils") UserUtilsInt userUtilsInt,
                             @Qualifier(value = "orderUtils") OrderUtilsInt orderUtils) {
        this.phoneService = phoneService;
        this.userService = userService;
        this.orderService = orderService;
        this.userUtilsInt = userUtilsInt;
        this.orderUtils = orderUtils;
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id, Model model) {
        model.addAttribute("imgUtil", userUtilsInt);
        return "profile/profile";
    }

    @GetMapping("/{id}/purchasesList")
    public String purchasesList(@PathVariable Long id, Model model, Authentication authentication) {
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        User user = principal.getUser();
        Order order = userUtilsInt.getPreparatoryOrder(user.getOrders());
        List<Phone> userPhones = new ArrayList<>();
        if (order != null) {
            userPhones = order.getPhones();
        }
        Map<String, Map<Phone, Integer>> phones = orderUtils.convertListOfPhonesIntoMap(userPhones);
        model.addAttribute("purchaseList", phones);
        model.addAttribute("userUtils", userUtilsInt);
        return "profile/purchasesList";
    }

    @GetMapping("/{id}/orders")
    public String ordersList(@PathVariable Long id, Model model, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();

        List<Order> orders = userUtilsInt.getOrdersWithoutPreparatory(user.getOrders());
        model.addAttribute("orders", orders);
        model.addAttribute("orderUtils", orderUtils);

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
    public String addPurchaseInPhoneCatalog(@PathVariable Long id, Authentication authentication,
                                            @RequestParam(value = "requestFrom", required = false) String requestFromParam) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = userUtilsInt.getPreparatoryOrder(user.getOrders());
        if (order == null) {
            order = new Order();
            order.setState(OrderState.PREPARATORY);
            order.setUser(user);
            userUtilsInt.addOrder(user.getOrders(), order);
        }
        Phone phoneById = phoneService.findById(id);
        orderUtils.addPhone(order.getPhones(), phoneById);


        orderService.save(order);
        if (requestFromParam.equals("showPhone")) {
            return "redirect:/phones/" + id;
        }
        return "redirect:/phones";
    }

    @PostMapping(value = "/{id}/plusOp")
    public String addPhoneInPlusOperation(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = userUtilsInt.getPreparatoryOrder(user.getOrders());
        Phone phoneById = phoneService.findById(id);
        orderUtils.addPhone(order.getPhones(), phoneById);

        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/purchasesList";
    }

    @PostMapping(value = "/{id}/order")
    public String makeAnOrder(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = userUtilsInt.getPreparatoryOrder(user.getOrders());
        order.setState(OrderState.ACTIVE);
        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/successOrder";
    }

    @PutMapping(value = "/{id}/orders")
    public String cancelTheOrder(@PathVariable long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = userUtilsInt.findOrder(user.getOrders(), id);
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
        Order order = userUtilsInt.getPreparatoryOrder(user.getOrders());
        orderUtils.deletePhone(order.getPhones(), id);

        orderService.save(order);
        return "redirect:/profile/" + securityUser.getUser().getId() + "/purchasesList";
    }

    @DeleteMapping(value = "/{id}/allPhones")
    public String removeAllPhonesInOneOrderFromPurchase(@PathVariable Long id, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Order order = userUtilsInt.getPreparatoryOrder(user.getOrders());
        orderUtils.deleteAllPhones(order.getPhones(), id);
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
        Order order = userUtilsInt.findOrder(user.getOrders(), id);
        if (order.getState().toString().equals("CANCELED")) {
            orderService.deleteById(id);
            userUtilsInt.deleteOrder(user.getOrders(), id);
            return "redirect:/profile/" + user.getId() + "/orders";
        }
        order.setState(OrderState.DELETED);
        orderService.save(order);
        return "redirect:/profile/" + user.getId() + "/orders";
    }
}
