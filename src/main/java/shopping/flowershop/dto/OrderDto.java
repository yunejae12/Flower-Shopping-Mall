package shopping.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shopping.flowershop.domain.Delivery;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.OrderItem;
import shopping.flowershop.domain.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Member member;
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Delivery delivery;
}
