package shopping.flowershop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shopping.flowershop.domain.Seller;

@Service
public class SellerServiceImpl  implements SellerService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public Seller join(Seller seller){
    return seller;

    }

    public void login(String id,String pw){

    }

}
