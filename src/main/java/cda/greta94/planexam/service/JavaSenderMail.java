package cda.greta94.planexam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaSenderMail {

    private JavaMailSender javaMailSender;

    private JetonResetMdpService jetonResetMdpService;

    @Autowired
    public JavaSenderMail(JavaMailSender javaMailSender, JetonResetMdpService jetonResetMdpService) {
        this.javaMailSender = javaMailSender;
        this.jetonResetMdpService = jetonResetMdpService;
    }

    public void sendPasswordResetEmail(String destinataire, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("PlanExam : Réinitialisation de mot de passe");
        message.setText("Cliquez sur <a href=" + "'" + generateResetPasswordLink(token) + "'" + ">ce lien</a> pour réinitialiser votre mot de passe");
        message.setTo(destinataire);
        message.setFrom("hamza.benketaf@gmail.com"); // L'adresse de l'expéditeur

        javaMailSender.send(message);
    }

    public String generateResetPasswordLink(String token) {
        return  "http://localhost:8080/reset-password?token="+token;
    }
}
