package by.yurachel.springapp.util.userUtils;

import by.yurachel.springapp.model.order.Order;

import java.util.List;

public interface UserUtilsInt {

    String getAvatarData(byte[] byteData);

    void editOrder(List<Order> orders, Order order);

    Order findOrder(List<Order> orders, long id);

    void addOrder(List<Order> orders, Order order);

    boolean containsPhoneInPreparatoryOrder(List<Order> orders, long id);

    List<Order> getOrdersWithoutPreparatory(List<Order> orders);

    Order getPreparatoryOrder(List<Order> orders);

    void deleteOrder(List<Order> orders, long id);
}
