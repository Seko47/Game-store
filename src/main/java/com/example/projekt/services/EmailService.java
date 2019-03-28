package com.example.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService
{

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationMail ( String to, String username, String activationCode )
    {
        try
        {
            StringBuilder stringBuilder = new StringBuilder ( "<html><head><title>Aktywacja konta</title></head><body>" );
            stringBuilder.append ( "<center><h2><b>Game Store</b></h2></center><HR>Witaj " )
                    .append ( username )
                    .append ( "!<hr>" )
                    .append ( "<a href='http://localhost:8080/registration/activate/" )
                    .append ( activationCode )
                    .append ( "'>Aktywuj konto</a>" )
                    .append ( "</body></html>" );
            MimeMessage message = mailSender.createMimeMessage ();

            message.setSubject ( "Rejestracja konta w sklepie" );
            MimeMessageHelper helper;
            helper = new MimeMessageHelper ( message, true );
            helper.setFrom ( "test46114@gmail.com" );
            helper.setTo ( to );
            helper.setText ( stringBuilder.toString (), true );
            mailSender.send ( message );
        }
        catch ( MessagingException ex )
        {
            System.out.println ( "Błąd wysłania maila" );
        }
    }
}
