package VO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String subject, String body) throws Exception {
        String to = "ftsand@naver.com";
        String from = "db970212@gmail.com";
        String password = "vgozknmybytnwaaz";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(from));
        mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        mail.setSubject(subject);
        mail.setText(body);

        Transport.send(mail);
    }
}
