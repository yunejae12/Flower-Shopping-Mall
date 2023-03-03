package shopping.flowershop.service;

import org.springframework.transaction.annotation.Transactional;
import shopping.flowershop.domain.Delivery;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.Order;
import shopping.flowershop.domain.OrderItem;

import java.util.HashMap;
import java.util.List;

public interface OrderService {

    @Transactional
    Long order(Long memberId, HashMap<Long, Integer> itemIdAndCounts);

    @Transactional
    void cancelOrder(Long orderId);
}
