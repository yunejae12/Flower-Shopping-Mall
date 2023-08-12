package shopping.flowershop.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieServiceImpl implements CookieService{
    @Override
    public Cookie createCookie(String cookieName, String value,int age) {
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(age);
        cookie.setPath("/");

        return cookie;
    }

    @Override
    public Cookie getCookie(HttpServletRequest request, String cookieName) {
        final Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                return cookie;
            }
        }
        return null;
    }
}
