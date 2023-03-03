package shopping.flowershop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import shopping.flowershop.service.MemberService;

@Configuration
public class SecurityConfig{
    private final MemberService memberService;
    //private final SellerService sellerService;
    public SecurityConfig(MemberService memberService){
        this.memberService = memberService;
    }
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests().mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().userDetailsService(memberService)
                .httpBasic();
        return http.build();

    }
   /* @Bean
    public SecurityFilterChain sellerFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/entrepreneur").hasRole("entrepreneur")
                .and().formLogin()
                .and().userDetailsService(sellerService);
        return http.build();
    }*/

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}