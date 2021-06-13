package com.eshop.backend.auth.mail;

import com.eshop.backend.dao.DataAccess.authorized_user.AuthorizedUserDao;
import com.eshop.backend.dao.DataAccess.email_token.EmailTokenDao;
import com.eshop.backend.dao.models.AuthorizedUser;
import com.eshop.backend.dao.models.EmailToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailSenderService {

    private final AuthorizedUserDao authorizedUsersDao;
    private final EmailTokenDao emailTokenDao;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(AuthorizedUserDao authorizedUsersDao, EmailTokenDao emailTokenDao, JavaMailSender mailSender) {
        this.authorizedUsersDao = authorizedUsersDao;
        this.emailTokenDao = emailTokenDao;
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(AuthorizedUser user, String type) {
        String token = UUID.randomUUID().toString();

        user = authorizedUsersDao.getByLogin(user.getUserLogin());
        EmailToken emailToken = new EmailToken(type, token, user.getId());
        emailTokenDao.createVerificationToken(user, emailToken);

        String recipientAddress = user.getUserLogin();

        String subject = "";
        String confirmationUrl = "";
        String message = "";

        if (type.equals("emailVerify")) {
            subject = "Registration Confirmation";
            confirmationUrl = "/verify-email?token=" + token;
            message = "To confirm your e-mail address, please click the link below:\n";
        }
        if (type.equals("resetPassword")) {
            subject = "Reset password Confirmation";
            confirmationUrl = "/reset-password?token=" + token;
            message = "Please click the below link to reset your password, the link will be valid for 1 day:\n";
        }

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:4200" + confirmationUrl);
        mailSender.send(email);
    }
}
