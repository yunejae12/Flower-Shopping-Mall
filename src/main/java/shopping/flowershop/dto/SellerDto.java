package shopping.flowershop.dto;

import shopping.flowershop.domain.Address;
import shopping.flowershop.domain.Item;
import shopping.flowershop.domain.SellerStatus;

import javax.persistence.*;
import java.util.List;

public class SellerDto {

    private Long id;
    private String loginId;
    private String password;
    private String enterpriseNumber;
    private String name;
    private List<Item> items;

    @Embedded
    private Address address;
    private String phoneNumber;
    private String email;
    private Double reputation;
    @Enumerated(EnumType.STRING)
    private SellerStatus status;
}
