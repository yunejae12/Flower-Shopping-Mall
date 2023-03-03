package shopping.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.flowershop.domain.*;
import shopping.flowershop.repository.ItemRepository;
import shopping.flowershop.repository.MemberRepository;
import shopping.flowershop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     * @param itemIdAndCounts 아이템의 아이디와 주문수량 */
    @Override
    @Transactional
    public Long order(Long memberId, HashMap<Long, Integer> itemIdAndCounts){
        List<OrderItem> orderItems = new ArrayList<>();
        Member member = memberRepository.findOne(memberId);
        for(Map.Entry<Long,Integer> e : itemIdAndCounts.entrySet()){
            Item item = itemRepository.findOne(e.getKey());
            OrderItem orderItem = new OrderItem(item,item.getPrice(),e.getValue());
            orderItems.add(orderItem);
        }

        Delivery delivery = new Delivery(member.getAddress(),DeliveryStatus.UNPAID);


        Order order = new Order(member,OrderStatus.ORDER,delivery,orderItems.toArray(OrderItem[]::new));
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Override
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public void showAllOrder(Long MemberId){

    }

    //검색
    //public List<Order> findOrder(OrderSearch orderSearch){
        //return OrderRepository.findAll(OrderSearch);
    //}



}
