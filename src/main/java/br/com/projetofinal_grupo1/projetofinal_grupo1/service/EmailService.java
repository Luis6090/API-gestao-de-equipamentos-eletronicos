package br.com.projetofinal_grupo1.projetofinal_grupo1.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailSimples(String destino, String assunto, String corpo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destino);
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);
        mensagem.setFrom("noreply@rarolabs.com");

        mailSender.send(mensagem);
    }
}