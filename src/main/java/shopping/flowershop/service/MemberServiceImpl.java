package shopping.flowershop.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopping.flowershop.domain.Member;
import shopping.flowershop.repository.MemberRepository;
import shopping.flowershop.security.JwtService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public Long join(Member member){
        member.changePassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.findByLoginId(member.getLoginId()).ifPresent(m -> {throw new IllegalArgumentException("이미 존재하는 회원입니다");});
        memberRepository.save(member);
        String jwtToken = jwtService.generateToken(member, (long)(60 * 1000 * 10));
        return member.getId();
    }

    @Override
    public String login(String loginId, String password) throws IllegalArgumentException {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("회원이 존재하지 않습니다.");
                });
        log.info("member={}",member.toString());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginId,password));
        return member.getName();
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

}
