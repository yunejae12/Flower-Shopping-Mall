package shopping.flowershop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
public class Member extends TimeBase {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String loginId;



    private String password;
    @Embedded
    private Address address;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "member")
    private List<ItemFavorites> favoredItem = new ArrayList<>();

    public void addFavorites(ItemFavorites itemFavorites){
        favoredItem.add(itemFavorites);
        itemFavorites.setMember(this);
    }
    public void changePassword(String password) {
        this.password = password;
    }
    @Builder
    public Member(String name,String loginId,String password,Address address,String email,String phoneNumber){
        super.creator = loginId;
        super.modifier = loginId;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }



}
