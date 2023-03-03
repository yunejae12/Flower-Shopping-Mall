package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.flowershop.domain.Member;
import shopping.flowershop.service.MemberService;
import shopping.flowershop.service.MemberServiceImpl;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //@PostMapping("/member")
    /*public Member signup(@ModelAttribute ){
        memberService.
        return memberService.join(member)
    }*/



}
