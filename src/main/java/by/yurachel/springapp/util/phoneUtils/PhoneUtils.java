package by.yurachel.springapp.util.phoneUtils;

import by.yurachel.springapp.model.order.Order;
import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.model.user.User;
import by.yurachel.springapp.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "phoneUtils")
public class PhoneUtils {

    private final IService<Order> orderIService;
    private final IService<User> userIService;

    public PhoneUtils(IService<Order> orderIService, IService<User> userIService) {
        this.orderIService = orderIService;
        this.userIService = userIService;
    }

    public void addImage(Phone phone, String imgLink) {
        phone.getImages().add(imgLink);
    }

    public void findUsersAndOrders(long id) {
        List<Order> orders = orderIService.findAll();
        for (Order order : orders) {
            order.getPhones().removeIf(phone -> phone.getId() == id);
            orderIService.save(order);
        }
        List<User> users = userIService.findAll();
        for (User user : users) {
            user.getBookmarks().removeIf(phone -> phone.getId() == id);
            userIService.save(user);
        }
    }
}
