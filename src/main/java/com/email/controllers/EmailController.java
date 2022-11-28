package com.email.controllers;

import com.email.dtos.EmailDto;
import com.email.models.EmailModel;
import com.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmailController {

    public final EmailService emailService;
    public EmailModel emailModel;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @GetMapping("/get-emails")
    public ResponseEntity<List<EmailModel>> getEmails(){
        return new ResponseEntity<>(emailService.getEmails(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
