package shopping.flowershop.repository;

import shopping.flowershop.domain.Order;
import shopping.flowershop.service.SearchService;

import java.util.List;

public interface OrderRepository {
    void save(Order order);

    Order findOne(Long orderId);

    List<Order> findAllByMemberByOrderByDateDesc(Long memberId);
}
