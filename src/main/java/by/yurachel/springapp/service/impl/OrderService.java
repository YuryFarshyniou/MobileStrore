package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.repository.OrderRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderService")
public class OrderService implements IService<Order> {

    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order findById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        logger.info("Order {} was successfully found", order);
        return order;
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        logger.info("All orders in db was successfully found");
        return orders;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        Order orderToBd = orderRepository.save(order);
        logger.info("Order {} was successfully added to db", orderToBd);

        return orderToBd;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        orderRepository.deleteById(id);
        logger.info("Order with id {} was successfully deleted form db", id);

    }

    @Override
    @Transactional
    public Page<Order> findAllPhones(Pageable pageable) {
        return null;
    }
}
