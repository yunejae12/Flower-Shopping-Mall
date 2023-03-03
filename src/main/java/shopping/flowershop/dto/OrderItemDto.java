package shopping.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shopping.flowershop.domain.Item;
import shopping.flowershop.domain.Order;

import javax.persistence.*;

@Getter
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private Order order;
    private Item item;
    private int orderPrice;
    private int count;
}
