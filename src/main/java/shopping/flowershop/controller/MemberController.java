package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shopping.flowershop.domain.Member;
import shopping.flowershop.domain.MemberRole;
import shopping.flowershop.dto.MemberDto;
import shopping.flowershop.repository.MemberRepository;
import shopping.flowershop.service.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/member/{memberId}")
    @ResponseBody
    public ResponseEntity<?> getMember(Long memberId){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String memberLoginId = user.getUsername();
        if(memberId != memberService.findByLoginId(memberLoginId).getId()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MemberDto.toDto(memberService.findOne(memberId)), HttpStatus.OK);
    }
    /*TODO:관리자 입장에서 API 어떻게 불러올지 생각해보기
    @PreAuthorize("hasRole('ROLE_ADMIN')")*/
    @GetMapping("/member")
    public List<Member> getMemberAll(){
        return memberService.findMembers();

    }
    @PutMapping("/member/{id}")
    public void updateMember(){

    }
    @DeleteMapping("/member/{id}")
    public void deleteMember(){

    }

    @PostMapping("/member")
    public String createMember(@ModelAttribute @Valid MemberDto memberForm, BindingResult bindingResult,
                               Model model,HttpSession session,
                               RedirectAttributes redirectAttributes) throws IOException {
        HashMap<String,String> validatorResult = new HashMap<>();
        /*아이디 인증 확인*/
        Boolean idConfirm = (Boolean) session.getAttribute("idConfirm");
        if(!idConfirm){
            validatorResult.putAll(getIdConfirmError(idConfirm));
        }
        if(bindingResult.hasErrors() || !validatorResult.isEmpty()){
            redirectAttributes.addFlashAttribute("memberForm",memberForm);
            /*유효성 통과를 못한 메세지 필터링*/
            validatorResult.putAll(getFieldErrors(bindingResult));
            redirectAttributes.addFlashAttribute("fieldErrorResult",validatorResult);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.memberForm", bindingResult);
            return "redirect:member/signup";
        }
        Member member = memberForm.toEntity();
        member.setRole(MemberRole.CUSTOMER);
        Long memberId = memberService.join(member);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/member/"+memberId).toUriString());
        return "/";
    }
    @PostMapping(value="/member/id-check")
    public ResponseEntity<Map<String, Object>> idCheck(@RequestBody Map<String, String> request,HttpSession httpSession){
        String loginId = request.get("loginId");
        Map<String, Object> response = new HashMap<>();

        if (memberRepository.findByLoginId(loginId).isPresent()) {
            httpSession.setAttribute("idConfirm",false);
            response.put("idConfirm", false);
            response.put("idConfirmText", "이미 사용 중인 아이디입니다.");
        } else {
            httpSession.setAttribute("idConfirm",true);
            response.put("idConfirm", true);
            response.put("idConfirmText", "해당 아이디는 사용 가능합니다.");
        }

        return ResponseEntity.ok(response);
    }



    private Map<String,String> getIdConfirmError(Boolean idConfirm){
        Map<String,String> validationResult = new HashMap<>();
        if(idConfirm == null){
            validationResult.put("idConfirmError","아이디 인증이 필요합니다.");
        }else if(!idConfirm){
            validationResult.put("idConfirmError","이미 사용 중인 아이디입니다.");
        }
        return validationResult;
    }

    private Map<String,String>  getFieldErrors(BindingResult bindingResult){
        Map<String,String> validationResult = new HashMap<>();
        bindingResult.getAllErrors().forEach(error ->{
            FieldError field =  (FieldError) error;
            String message = error.getDefaultMessage();
            validationResult.put(field.getField(),message);
        });
        return validationResult;
    }


}
