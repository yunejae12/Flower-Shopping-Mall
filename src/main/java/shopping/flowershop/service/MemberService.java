package shopping.flowershop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import shopping.flowershop.domain.Member;

import java.util.List;

public interface MemberService extends UserDetailsService {
    Long join(Member member);
    List<Member> findMembers();
    Member findOne(Long id);
    Member findByLoginId(String id);

}
