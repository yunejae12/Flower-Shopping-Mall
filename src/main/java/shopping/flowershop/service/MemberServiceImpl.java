package shopping.flowershop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.flowershop.domain.Member;
import shopping.flowershop.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public Long join(Member member){
        member.changePassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.findByLoginId(member.getLoginId()).ifPresent(m -> {throw new IllegalArgumentException("이미 존재하는 회원입니다");});
        memberRepository.save(member);
        return member.getId();
    }

    //회원전체조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
    public Member findByLoginId(String id){
        return memberRepository.findByLoginId(id).orElseThrow(()->{throw new IllegalArgumentException("loginId가 일치하는 회원이 없습니다.");});
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(()->{throw new UsernameNotFoundException("아이디 또는 비밀번호가 틀립니다.");});
        return User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .roles("customer")
                .build();
    }

}
