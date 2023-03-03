package shopping.flowershop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SellerService{
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
