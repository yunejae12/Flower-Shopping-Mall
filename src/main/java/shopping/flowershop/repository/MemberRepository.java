package shopping.flowershop.repository;

import shopping.flowershop.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Member findOne(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
    List<Member> findByName(String name);


}
