package by.yurachel.springapp.repository;

import by.yurachel.springapp.model.order.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
