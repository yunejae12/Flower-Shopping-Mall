package shopping.flowershop.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    Cookie createCookie(String cookieName, String value,int age);
    Cookie getCookie(HttpServletRequest request, String cookieName);
}
