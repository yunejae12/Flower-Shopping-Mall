package shopping.flowershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shopping.flowershop.domain.Address;
import shopping.flowershop.domain.DeliveryStatus;

@Getter
@AllArgsConstructor
@Builder
public class DeliveryDto {
    private Long id;
    private Address address;
    private DeliveryStatus status;
}
