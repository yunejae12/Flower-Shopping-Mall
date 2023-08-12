package shopping.flowershop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem extends TimeBase {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY  )
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;
    private int count;

    public OrderItem(Item item,int orderPrice,int count){
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
        item.removeStock(count);
    }

    public void cancel(OrderStatus orderStatus) {
        if(orderStatus == OrderStatus.ORDER){
            getItem().addStock(count);
        }else{
            throw new IllegalArgumentException("환불되거나 취소된 상품을 취소할 수는 없습니다.");
        }


    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}
