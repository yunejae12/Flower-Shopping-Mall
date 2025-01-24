package shopping.flowershop.service;

import org.springframework.validation.Errors;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.MemberRole;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface MemberService{
    Long join(Member member);
    String login(String loginId, String password);

    List<Member> findMembers();
    Member findOne(Long id);
    Member findByLoginId(String id);

    boolean memberAuthorizationValidation(@Nullable MemberRole role);
}
