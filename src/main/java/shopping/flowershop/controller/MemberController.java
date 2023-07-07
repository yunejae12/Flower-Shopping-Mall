package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.MemberRole;
import shopping.flowershop.dto.MemberDto;
import shopping.flowershop.service.MemberService;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //@PostMapping("/member/login")
    /*public Member signup(@ModelAttribute ){
        memberService.
        return memberService.join(member)
    }*/
    @GetMapping("/member/{memberId}")
    public Member getMember(Long memberId){
        Member member = memberService.findOne(memberId);
        return member;
    }

    @PostMapping("/member")
    public ResponseEntity<String> createMember(@ModelAttribute MemberDto memberForm){
        Member member = memberForm.toEntity();
        member.setRole(MemberRole.CUSTOMER);
        return ResponseEntity.ok(memberService.join(member));
    }



}
