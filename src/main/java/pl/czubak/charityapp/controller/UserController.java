package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.czubak.charityapp.entity.Category;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.model.PasswordDTO;
import pl.czubak.charityapp.repository.CategoryRepository;
import pl.czubak.charityapp.repository.DonationRepository;
import pl.czubak.charityapp.repository.InstitutionRepository;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"fullName", "id"})
public class UserController {

  private UserRepository userRepository;
  private UserService userService;
  private DonationRepository donationRepository;
  private CategoryRepository categoryRepository;
  private InstitutionRepository institutionRepository;

  public UserController(
      UserRepository userRepository,
      UserService userService,
      DonationRepository donationRepository,
      CategoryRepository categoryRepository,
      InstitutionRepository institutionRepository) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.donationRepository = donationRepository;
    this.categoryRepository = categoryRepository;
    this.institutionRepository = institutionRepository;
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
  public String processEditPassword(@Valid @ModelAttribute PasswordDTO passwordDTO, BindingResult result, HttpSession ses) {
    Long sesID = (Long) ses.getAttribute("id");
    User currentUser = userRepository.findById(sesID).get();
    if(result.hasErrors()){
      return "user-edit-password-page";
    }
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
  public String getUserDonationsList(Model model, HttpSession ses) {
    Long sesID = (Long) ses.getAttribute("id");
    List<Donation> userDonationList = donationRepository.findAllByisArchivedAndUserId(false, sesID);
    model.addAttribute("userDonationList", userDonationList);
    return "user-donation-list-page";
  }

  @GetMapping("/donation/archive/{id}")
  public String archiveDonation(@PathVariable Long id) {
    Donation donation = donationRepository.findById(id).get();
    donation.setArchived(true);
    donationRepository.save(donation);
    return "redirect:/user/donations?successarchive";
  }

  @GetMapping("/donations/archived/list")
  public String getArchivedDonationList(Model model, HttpSession ses) {
    Long sesID = (Long) ses.getAttribute("id");
    List<Donation> archivedDonationList =
        donationRepository.findAllByisArchivedAndUserId(true, sesID);
    model.addAttribute("archivedDonationList", archivedDonationList);
    return "user-archived-donation-list-page";
  }

  @GetMapping("/donation/remove/{id}")
  public String removeDonationByID(@PathVariable Long id) {
    Donation currentDonation = donationRepository.findById(id).get();
    if (currentDonation.getStatus().getName().equals("Zlozone")) {
      donationRepository.delete(currentDonation);
      return "redirect:/user/donations?success";
    } else {
      return "redirect:/user/donations?error";
    }
  }

  @GetMapping("/donation/details/{id}")
  public String getDonationDetails(@PathVariable Long id, Model model) {
    Donation currentDonation = donationRepository.findById(id).get();
    model.addAttribute("currentDonation", currentDonation);
    return "user-donation-details-page";
  }

  @GetMapping("/donation/edit/{id}")
  public String editDonation(@PathVariable Long id, Model model, Principal principal) {
    Donation currentDonation = donationRepository.findById(id).get();
    if (currentDonation.getStatus().getName().equals("Zlozone")) {
      User currentUser = userRepository.findByEmail(principal.getName());
      model.addAttribute("donation", currentDonation);
      model.addAttribute("categories", categoryRepository.findAll());
      model.addAttribute("institutions", institutionRepository.findAll());
      model.addAttribute("fullName", currentUser.getFullName());
      model.addAttribute("id", currentUser.getId());
      return "form";
    } else {
      return "redirect:/user/donations?fail";
    }
  }
}
