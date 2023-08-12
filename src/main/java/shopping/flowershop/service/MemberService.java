package shopping.flowershop.service;

import shopping.flowershop.domain.Member;

import java.util.List;

public interface MemberService{
    Long join(Member member);
    String login(String loginId, String password);

    List<Member> findMembers();
    Member findOne(Long id);
    Member findByLoginId(String id);

}
