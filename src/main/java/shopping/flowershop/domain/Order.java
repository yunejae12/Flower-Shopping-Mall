package shopping.flowershop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "ORDERS")
public class Order extends TimeBase {
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    @Builder
    public Order(Member member, OrderStatus orderStatus,Delivery delivery,OrderItem... orderItems){
        for (OrderItem orderItem : orderItems){
            this.addItem(orderItem);
        }
        this.createdTime = LocalDateTime.now();
        this.member = member;
        this.status = orderStatus;
        this.delivery = delivery;
    }

    public void addItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /** 주문 취소 */
    public void cancel(){
        if(this.getDelivery().getStatus() == DeliveryStatus.COMPLETED){
            throw new IllegalArgumentException("이미 주문이 완료된 상품은 배송이 불가능 합니다");
        }
        for(OrderItem orderItem : this.getOrderItems()){
            orderItem.cancel(this.getStatus());
        }
        this.setStatus(OrderStatus.CANCEL);
        this.modifiedTime = LocalDateTime.now();
    }

    /**전체 주문한 상품 합계 조회 */
    public int getTotalPrice() {
        return this.getOrderItems().stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }


}
