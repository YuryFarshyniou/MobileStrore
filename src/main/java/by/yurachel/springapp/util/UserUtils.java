package by.yurachel.springapp.util;

import by.yurachel.springapp.model.order.impl.Order;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class UserUtils {

    public String getAvatarData(byte[] byteDate) {
        return Base64.getMimeEncoder().encodeToString(byteDate);
    }

    public  void editOrder(List<Order> orders, Order order) {
        orders.forEach(o -> {
            if (o.getId() == order.getId()) {
                o.setState(order.getState());
            }
        });
    }

    public  Order findOrder(List<Order> orders, long id) {
        return orders.stream().filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public  void addOrder(List<Order> orders, Order order) {
        orders.add(order);
    }
}
