package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.MailMessageDto;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendMailService {
    void sendEmail(MailMessageDto mailMessageDto);
}
