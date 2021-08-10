package by.yurachel.springapp.repository.orderRepository;

import by.yurachel.springapp.model.order.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
