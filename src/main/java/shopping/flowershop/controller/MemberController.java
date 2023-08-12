package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.MemberRole;
import shopping.flowershop.dto.MemberDto;
import shopping.flowershop.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;


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
        return memberService.findOne(memberId);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/member")
    public List<Member> getMemberAll(){
        return memberService.findMembers();

    }

    @PostMapping("/member")
    public ResponseEntity<Member> createMember(@ModelAttribute MemberDto memberForm,Model model,HttpServletResponse response) throws IOException {
        Member member = memberForm.toEntity();
        member.setRole(MemberRole.CUSTOMER);
        Long memberId = memberService.join(member);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/member/"+memberId).toUriString());
        /*if(!Boolean.TRUE.equals(model.getAttribute("idCheck"))){
            model.getAttribute()
        }*/
        response.sendRedirect("/member/login");
        return ResponseEntity.created(uri).body(member);
    }
    @PutMapping("/member/{id}")
    public void updateMember(){

    }
    @DeleteMapping("/member/{id}")
    public void deleteMember(){

    }
    /*@GetMapping("/token/refresh")
    public ResponseEntity<String> getRefreshToken(HttpServletRequest request, HttpServletResponse response){
        String jwtHeader = request.getHeader("Authorization");
    }*/
    /*@PostMapping("/member/id-check")
    public void idCheck(String loginId,Model model){
        try{
            memberService.findByLoginId(loginId);
            model.addAttribute("idCheck",true);
        }catch (IllegalArgumentException e){
            model.addAttribute("idCheck",false);
        }
    }
     */




}
