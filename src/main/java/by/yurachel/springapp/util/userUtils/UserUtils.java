package by.yurachel.springapp.util.userUtils;

import by.yurachel.springapp.model.order.Order;
import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.util.orderUtils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "userUtils")
public class UserUtils {
    private final OrderUtils orderUtils;

    public UserUtils(OrderUtils orderUtils) {
        this.orderUtils = orderUtils;
    }




    public void editOrder(List<Order> orders, Order order) {
        orders.forEach(o -> {
            if (o.getId() == order.getId()) {
                o.setState(order.getState());
            }
        });
    }


    public Order findOrder(List<Order> orders, long id) {
        return orders.stream().filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addOrder(List<Order> orders, Order order) {
        orders.add(order);
    }


    public boolean containsPhoneInPreparatoryOrder(List<Order> orders, long id) {

        return orders.stream().filter(order2 -> OrderState.PREPARATORY.equals(order2.getState()))
                .findFirst()
                .map(order1 -> orderUtils.containsPhone(order1.getPhones(),id))
                .orElse(false);
    }


    public List<Order> getOrdersWithoutPreparatory(List<Order> orders) {
        return orders.stream().filter(order -> order.getState() != OrderState.PREPARATORY)
                .filter(order -> order.getState() != OrderState.DELETED)
                .collect(Collectors.toList());
    }


    public Order getPreparatoryOrder(List<Order> orders) {
        return orders.stream().filter(order -> order.getState().toString().equals("PREPARATORY"))
                .findFirst()
                .orElse(null);
    }


    public void deleteOrder(List<Order> orders, long id) {
        orders.removeIf(order -> order.getId() == id);
    }
}
