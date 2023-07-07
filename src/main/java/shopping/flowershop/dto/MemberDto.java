package shopping.flowershop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import shopping.flowershop.domain.Address;
import shopping.flowershop.domain.ItemFavorites;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.MemberRole;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Data
public class MemberDto{
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private MemberRole role;
    private Address address;
    private String phoneNumber;
    private String email;
    private List<ItemFavorites> favoredItem;

    public MemberDto() {

    }

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .role(role)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }

}
