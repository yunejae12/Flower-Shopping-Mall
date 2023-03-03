package shopping.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    JavaMailSender emailSender;
    SpringTemplateEngine templateEngine;

    @Override
    public void  sendMessage(String to, HashMap<String,String> templateValues)throws Exception{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("noreply@flowershop.com");
        helper.setTo(to);

        Context context = new Context();
        templateValues.forEach((key,value)->{context.setVariable(key,value);});

        String html = templateEngine.process("verification-email",context);
        helper.setText(html,true);
        emailSender.send(message);

    }
}
