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

    @Autowired
    private Configuration freemarkerConfig;

    public void sendEmail(MailMessageDto messageDto) {

         try {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


             MimeMessage message = emailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(message);
             freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
             Template temp = freemarkerConfig.getTemplate("email-template.ftl");

             Map<String, Object> model = new HashMap<>();
             model.put("name", messageDto.getUserName());
             model.put("text", messageDto.getText());
             model.put("country", messageDto.getCountry());
             model.put("city", messageDto.getCity());
             model.put("office", messageDto.getOffice());
             model.put("placeNumber", messageDto.getPlaceNumber());
             model.put("timeStart", messageDto.getTimeStart().format(formatter));
             model.put("timeEnd", messageDto.getTimeEnd().format(formatter));

             String text = FreeMarkerTemplateUtils.processTemplateIntoString(temp, model);
             helper.setTo(messageDto.getEmail());
             helper.setText(text, true);
             helper.setSubject("Placebook notification");

             this.emailSender.send(message);
         }

         catch (TemplateException e) {
             throw new SendMessageException("Send email exception! TemplateException");
         }
         catch (IOException e) {
             throw new SendMessageException("Send email exception! IOException");
         }
         catch (MessagingException e) {
             throw new SendMessageException("Send email exception! MessagingException");
         }


    }
}
