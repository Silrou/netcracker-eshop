package com.eshop.backend.auth.mail;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.dao.email.EmailTokenDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.auth.dao.models.EmailTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmailSenderService {

    private final AuthorizedUserDao authorizedUsersdao;
    private final EmailTokenDao emailTokendao;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(AuthorizedUserDao authorizedUsersdao, EmailTokenDao emailTokendao, JavaMailSender mailSender) {
        this.authorizedUsersdao = authorizedUsersdao;
        this.emailTokendao = emailTokendao;
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(AuthorizedUserModel user) {
        String token = UUID.randomUUID().toString();

        EmailTokenModel emailTokenModel = new EmailTokenModel(token, user.getId());
        emailTokendao.createVerificationToken(user, emailTokenModel);

        String recipientAddress = user.getUserLogin();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/user/confirm-account?token=" + token;
        String message = "To confirm your e-mail address, please click the link below:\n";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8081" + confirmationUrl);
        mailSender.send(email);
    }
}
