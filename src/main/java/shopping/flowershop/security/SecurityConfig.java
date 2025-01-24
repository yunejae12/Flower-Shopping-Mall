package shopping.flowershop.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import shopping.flowershop.security.filter.CustomAuthenticationFilter;
import shopping.flowershop.security.filter.CustomAuthorizationFilter;
import shopping.flowershop.service.UserDetailsServiceImpl;

@Configuration

public class SecurityConfig{
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationFilter authenticationFilter;
    private final CustomAuthorizationFilter authorizationFilter;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    //private final SellerService sellerService;
    public SecurityConfig(UserDetailsServiceImpl detailsService,
                          AuthenticationProvider authenticationProvider,
                          CustomAuthenticationFilter authenticationFilter,
                          CustomAuthorizationFilter authorizationFilter,
                          LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler){
        this.userDetailsService = detailsService;
        this.authenticationProvider = authenticationProvider;
        this.authenticationFilter = authenticationFilter;
        this.authorizationFilter = authorizationFilter;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailureHandler = loginFailureHandler;
    }
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    authenticationFilter.setFilterProcessesUrl("/member/login");
    authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/member/login","POST"));
    authenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);

        http
                .csrf().disable()
                .authorizeRequests().mvcMatchers("/","/member/login/**","/member/signup","/member","/member/id-check","/error").permitAll().and()
                .authorizeRequests().mvcMatchers("/js/**","/fonts/**","/img/**","/css/**","/sass/**","/Source/**").permitAll().and()
                .authorizeRequests().mvcMatchers("/shop-details","/shop-grid","/shoping-cart").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilter(authenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage("/member/login")
                //.successHandler(loginSuccessHandler)
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout().logoutSuccessUrl("/").and()
                .userDetailsService(userDetailsService);
        return http.build();


    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
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
}