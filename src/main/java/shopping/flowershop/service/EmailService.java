package shopping.flowershop.service;

import java.util.HashMap;

public interface EmailService {
    void  sendMessage(String to, HashMap<String,String> templateValues)throws Exception;
}
