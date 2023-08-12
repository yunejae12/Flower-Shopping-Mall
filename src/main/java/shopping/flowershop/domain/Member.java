package shopping.flowershop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
public class Member extends TimeBase implements UserDetails {
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

    @Setter
    @Enumerated(EnumType.STRING)
    private MemberRole role;

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
    public Member(String name, String loginId, String password,MemberRole role, Address address, String email, String phoneNumber){
        super.creator = loginId;
        super.modifier = loginId;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
