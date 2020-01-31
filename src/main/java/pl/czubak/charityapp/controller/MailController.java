package pl.czubak.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.model.Error;
import pl.czubak.charityapp.model.Sender;
import pl.czubak.charityapp.service.MailService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/message")
public class MailController {

  private MailService mailService;

  @Autowired
  Validator validator;

  public MailController(MailService mailService) {
    this.mailService = mailService;
  }

  @PostMapping
  public String getMessage(
          @RequestParam String name, @RequestParam String surname, @RequestParam String message, Model model) {
    Sender sender = new Sender(name,surname,message);
    Set<ConstraintViolation<Sender>> violations = validator.validate(sender);
    if(!violations.isEmpty()){
      List<Error> errorList = new ArrayList<>();
      for (ConstraintViolation<Sender> constraintViolation : violations) {
        errorList.add(new Error(constraintViolation.getMessage())); }
      model.addAttribute("contactErrorList", errorList);
      return "contact-form-error-list";
    }
    mailService.sendSimpleMessage("charityappbyjczubak@gmail.com", name + " " + surname, message);
    return "mail-confirmation";
  }
}
