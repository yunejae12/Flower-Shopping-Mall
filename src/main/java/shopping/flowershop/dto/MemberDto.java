package shopping.flowershop.dto;


import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import shopping.flowershop.domain.Address;
import shopping.flowershop.domain.ItemFavorites;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.MemberRole;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@Builder
@Data

public class MemberDto{
    private Long id;
    private String name;
    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]{5,15}",message = "아이디는 특수 문자를 제외한 알파벳 5-15자 입니다.")
    private String loginId;
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "\"(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}",message = "비밀번호는 8~16자 영문 대소문자,숫자를 입력해주세요")
    private String password;
    private String passwordConfirm;

    private MemberRole role;
    private Address address;
    @Pattern(regexp = "^(010|\\d{2,3})\\d{3,4}\\d{4}$",message = "전화번호는 \"-\" 없이 숫자열로 10-11자리 입력해주세요")
    private String phoneNumber;
    @Email(message="올바르지 않은 이메일 형식입니다.")
    private String email;
    private List<ItemFavorites> favoredItem;


    private boolean passwordChecked;

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

    public static MemberDto toDto(Member m){
        return MemberDto.builder()
                .id(m.getId())
                .name(m.getName())
                .loginId(m.getLoginId())
                .password(m.getPassword())
                .role(m.getRole())
                .address(m.getAddress())
                .phoneNumber(m.getPhoneNumber())
                .email(m.getEmail())
                .favoredItem(m.getFavoredItem()).build();
    }

    @AssertTrue(message = "비밀번호 확인이 일치하지 않습니다.")
    public boolean isPasswordChecked(){
        if(this.getPassword() == null) return false;
        return getPassword().equals(getPasswordConfirm());
    }
}
