package shopping.flowershop.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if(exception instanceof BadCredentialsException){
            errorMessage = "아이디나 비밀번호가 맞지 않습니다.";
        }else if(exception instanceof InternalAuthenticationServiceException){
            errorMessage = "내부적으로 발생한 시스템 문제떄문에 로그인 할 수 없습니다.";
        }else if(exception instanceof UsernameNotFoundException){
            errorMessage = "아이디나 비밀번호가 맞지 않습니다.";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException){
            errorMessage = "인증요청이 거부되었습니다. 관리자에게 문의하여 주십시오.";
        }else{
            errorMessage = "알수없는 이유로 로그인이 실패하였습니다. 관리자에게 문의하여 주십시오.";
        }
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage",errorMessage);
        setDefaultFailureUrl("/member/login");
        super.onAuthenticationFailure(request, response, exception);
    }
}
