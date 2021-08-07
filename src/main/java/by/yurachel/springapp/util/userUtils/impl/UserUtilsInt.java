package by.yurachel.springapp.util.userUtils.impl;

import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.util.orderUtils.OrderUtilsInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "userUtils")
public class UserUtilsInt implements by.yurachel.springapp.util.userUtils.UserUtilsInt {
    public static UserUtilsInt userUtilsInt;
    private OrderUtilsInt orderUtils;

    @Autowired
    public void setOrderUtils(@Qualifier(value = "orderUtils") OrderUtilsInt orderUtils) {
        this.orderUtils = orderUtils;
    }


    private UserUtilsInt() {
    }

    public static UserUtilsInt getUserUtils() {
        if (userUtilsInt == null) {
            userUtilsInt = new UserUtilsInt();
        }
        return userUtilsInt;
    }

    @Override
    public String getAvatarData(byte[] byteDate) {
        return Base64.getMimeEncoder().encodeToString(byteDate);
    }

    @Override
    public void editOrder(List<Order> orders, Order order) {
        orders.forEach(o -> {
            if (o.getId() == order.getId()) {
                o.setState(order.getState());
            }
        });
    }

    @Override
    public Order findOrder(List<Order> orders, long id) {
        return orders.stream().filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addOrder(List<Order> orders, Order order) {
        orders.add(order);
    }

    @Override
    public boolean containsPhoneInPreparatoryOrder(List<Order> orders, long id) {
        Order order = orders.stream().filter(order2 -> order2.getState().toString().equals("PREPARATORY"))
                .findFirst()
                .orElse(null);
        if (order == null) {
            return false;
        }
        return orderUtils.containsPhone(order.getPhones(), id);
    }

    @Override
    public List<Order> getOrdersWithoutPreparatory(List<Order> orders) {
        return orders.stream().filter(order -> order.getState() != OrderState.PREPARATORY)
                .filter(order -> order.getState() != OrderState.DELETED)
                .collect(Collectors.toList());
    }

    @Override
    public Order getPreparatoryOrder(List<Order> orders) {
        return orders.stream().filter(order -> order.getState().toString().equals("PREPARATORY"))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteOrder(List<Order> orders, long id) {
        orders.removeIf(order -> order.getId() == id);
    }
}
