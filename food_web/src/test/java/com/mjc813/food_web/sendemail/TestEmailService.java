package com.mjc813.food_web.sendemail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmailService {
    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail() {
        String to = "softagape@gmail.com";
        String subject = "FoodWeb homepage send to you about user init password !";
        String text = "you can use new password = ";
        this.emailService.sendSimpleMail(to, subject, text);
    }
}
