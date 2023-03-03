package shopping.flowershop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shopping.flowershop.domain.Address;
import shopping.flowershop.domain.ItemFavorites;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private Address address;
    private String phoneNumber;
    private String email;
    private List<ItemFavorites> favoredItem;

}
