package com.exadel.placebook.service;

import com.exadel.placebook.model.dto.MailMessageDto;

public interface SendMailService {
    void sendEmail(MailMessageDto mailMessageDto);
}
