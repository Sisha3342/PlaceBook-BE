package com.exadel.placebook.service.impl;

import com.exadel.placebook.model.dto.MailMessageDto;
import com.exadel.placebook.model.exception.SendMessageException;
import com.exadel.placebook.service.SendMailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    public JavaMailSender emailSender;


    public void sendEmail(MailMessageDto messageDto) {

         try {

             MimeMessage message = emailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(message);
             helper.setTo(messageDto.getEmail());
             helper.setText(messageDto.getText(), true);
             helper.setSubject("Placebook notification");
             this.emailSender.send(message);
         }

         catch (MessagingException e) {
             throw new SendMessageException("Send email exception! MessagingException");
         }


    }
}
