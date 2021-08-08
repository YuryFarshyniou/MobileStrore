package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.repository.OrderRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderService implements IService<Order> {

    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "orderId", key = "#id")
    public Order findById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        logger.info("Order {} was successfully found", order.getId());
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "ordersF")
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        logger.info("All orders in db was successfully found");
        return orders;
    }

    @Override
    @CachePut(cacheNames = "orderId", key = "#order.id")
    public Order save(Order order) {
        Order orderToBd = orderRepository.save(order);
        logger.info("Order {} was successfully added to db", orderToBd.getId());

        return orderToBd;
    }

    @Override
    @CacheEvict(cacheNames = "orderId", key = "#id")
    public void deleteById(long id) {
        orderRepository.deleteById(id);
        logger.info("Order with id {} was successfully deleted form db", id);

    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "ordersP")
    public Page<Order> findAllWithPagination(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        logger.info("All orders in db was successfully found");
        return orders;
    }
}
