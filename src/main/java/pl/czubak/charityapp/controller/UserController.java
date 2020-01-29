package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.model.PasswordDTO;
import pl.czubak.charityapp.repository.DonationRepository;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"fullName", "id"})
public class UserController {

  private UserRepository userRepository;
  private UserService userService;
  private DonationRepository donationRepository;

  public UserController(UserRepository userRepository, UserService userService, DonationRepository donationRepository) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.donationRepository=donationRepository;
  }

  @GetMapping("/edit")
  public String editUser(Model model, HttpSession ses) {
    Long sesID = (Long) ses.getAttribute("id");
    User currentUser = userRepository.findById(sesID).get();
    model.addAttribute("user", currentUser);
    return "user-edit-page";
  }

  @PostMapping("/edit")
  public String processEditUser(@ModelAttribute User user, HttpSession ses, Model model) {
    userService.updateUser(user);
    ses.removeAttribute("fullName");
    ses.removeAttribute("id");
    model.addAttribute("fullName", user.getFullName());
    model.addAttribute("id", user.getId());
    return "redirect:/user/edit?success";
  }

  @GetMapping("/edit/password")
  public String editPassword(Model model) {
    model.addAttribute("passwordDTO", new PasswordDTO());
    return "user-edit-password-page";
  }

  @PostMapping("/edit/password")
  public String processEditPassword(@ModelAttribute PasswordDTO passwordDTO, HttpSession ses) {
    Long sesID = (Long) ses.getAttribute("id");
    User currentUser = userRepository.findById(sesID).get();
    if (!passwordDTO.getPassword().equals(passwordDTO.getRePassword())) {
      return "redirect:/user/edit/password?error";
    }
    if (userService.editUserPassword(currentUser, passwordDTO)) {
      return "redirect:/user/edit/password?success";
    } else {
      return "redirect:/user/edit/password?error";
    }
  }


  @GetMapping("/donations")
  public String getUserDonationsList(Model model, HttpSession ses){
    Long sesID = (Long) ses.getAttribute("id");
    List<Donation> userDonationList = donationRepository.findAllByUserId(sesID);
    model.addAttribute("userDonationList", userDonationList);
    return "user-donation-list-page";
  }
}
