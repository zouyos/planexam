package cda.greta94.planexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        //Mailtrap(aussi aller voir application.properties)
        javaMailSender.setHost("sandbox.smtp.mailtrap.io"); // Changez-le en fonction de votre fournisseur de messagerie
        javaMailSender.setPort(2525); // Changez-le en fonction de votre fournisseur de messagerie
        //GMAIL (aussi aller voir application.properties)
        //javaMailSender.setHost("smtp.gmail.com");
        //javaMailSender.setPort(587);

        javaMailSender.setUsername("ec4d7bd225ca5b");
        javaMailSender.setPassword("f2dd09b1b9c490");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return javaMailSender;
    }
}
