package pl.czubak.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.czubak.charityapp.service.MailService;

@Controller
@RequestMapping("/user/message")
public class MailController {

  private MailService mailService;

  public MailController(MailService mailService) {
    this.mailService = mailService;
  }

  @PostMapping
  public String getMessage(@RequestParam String name, @RequestParam String surname, @RequestParam String message) {
    mailService.sendSimpleMessage("charityappbyjczubak@gmail.com", name + " " + surname, message);
    return "mail-confirmation";
  }
}
