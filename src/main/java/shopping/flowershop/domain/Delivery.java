package shopping.flowershop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Delivery extends TimeBase {

    @Id @GeneratedValue
    @Column(name="DELIVERY_ID")
    private Long id;

    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public Delivery(Address address,DeliveryStatus status){
        this.address = address;
        this.status = status;
    }



}
