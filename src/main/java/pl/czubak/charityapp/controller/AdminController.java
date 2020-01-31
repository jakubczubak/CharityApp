package pl.czubak.charityapp.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.Institution;
import pl.czubak.charityapp.entity.Status;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.model.PasswordDTO;
import pl.czubak.charityapp.repository.DonationRepository;
import pl.czubak.charityapp.repository.InstitutionRepository;
import pl.czubak.charityapp.repository.StatusRepository;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.DonationService;
import pl.czubak.charityapp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"fullName", "id"})
public class AdminController {

  private UserRepository userRepository;
  private UserService userService;
  private DonationService donationService;
  private InstitutionRepository institutionRepository;
  private DonationRepository donationRepository;
  private StatusRepository statusRepository;

  public AdminController(
      UserRepository userRepository,
      DonationService donationService,
      InstitutionRepository institutionRepository,
      UserService userService,
      DonationRepository donationRepository,
      StatusRepository statusRepository) {
    this.userRepository = userRepository;
    this.donationService = donationService;
    this.institutionRepository = institutionRepository;
    this.userService = userService;
    this.donationRepository = donationRepository;
    this.statusRepository = statusRepository;
  }

  @GetMapping
  public String getAdminPage(Model model, Principal principal) {
    User currentUser = userRepository.findByEmail(principal.getName());
    model.addAttribute("fullName", currentUser.getFullName());
    model.addAttribute("id", currentUser.getId());
    model.addAttribute("donationAmount", donationService.donationAmount());
    model.addAttribute("AmountOfGoodPeople", donationService.numberOfGoodPeople());
    model.addAttribute("AmountOfTrustedInstitution", institutionRepository.findAll().size());
    model.addAttribute("AmountOfAdmins", userRepository.findAllByisAdminAndEnabled(true, 1).size());
    return "admin-main-page";
  }

  @GetMapping("/institutions")
  public String getInstitutionsList(Model model) {
    List<Institution> institutions = institutionRepository.findAll();
    model.addAttribute("institutions", institutions);
    return "admin-institution-list";
  }

  @GetMapping("/institution/remove/{id}")
  public String deleteInstitutionByID(@PathVariable Long id) {
    institutionRepository.delete(institutionRepository.getOne(id));
    return "redirect:/admin/institutions?successremove";
  }

  @GetMapping("/institution/add")
  public String addInstitution(Model model) {
    model.addAttribute("institution", new Institution());
    return "admin-institution-add-page";
  }

  @PostMapping("/institution/add")
  public String processAddInstitution(
      @Valid @ModelAttribute Institution institution, BindingResult result) {
    if (result.hasErrors()) {
      return "admin-institution-add-page";
    }
    institutionRepository.save(institution);
    return "redirect:/admin/institutions?successadd";
  }

  @GetMapping("/institution/edit/{id}")
  public String editInstitution(@PathVariable Long id, Model model) {
    Institution currentInstitution = institutionRepository.findById(id).get();
    model.addAttribute("institution", currentInstitution);
    return "admin-institution-edit-page";
  }

  @PostMapping("/institution/edit/")
  public String processEditInstitution(
      @Valid @ModelAttribute Institution institution, BindingResult result) {
    if (result.hasErrors()) {
      return "admin-institution-edit-page";
    }
    institutionRepository.save(institution);
    return "redirect:/admin/institutions?successedit";
  }

  @GetMapping("/users")
  public String getUsersList(Model model) {
    model.addAttribute("users", userRepository.findAllByisAdminAndEnabled(false, 1));
    return "admin-user-list";
  }

  @GetMapping("/user/remove/{id}")
  public String deleteUserByID(@PathVariable Long id) {
    userRepository.delete(userRepository.getOne(id));
    return "redirect:/admin/users?successremove";
  }

  @GetMapping("/user/edit/{id}")
  public String editUser(@PathVariable Long id, Model model) {
    User currentUser = userRepository.findById(id).get();
    model.addAttribute("user", currentUser);
    return "admin-user-edit-page";
  }

  @PostMapping("/user/edit")
  public String editUser(@Valid @ModelAttribute User user, BindingResult result) {
    if (result.hasErrors()) {
      return "admin-user-edit-page";
    }
    userService.updateUser(user);
    return "redirect:/admin/users?successedit";
  }

  @GetMapping("/user/block/{id}")
  public String blockUser(@PathVariable Long id) {
    User userToBlock = userRepository.findById(id).get();
    userToBlock.setEnabled(0);
    userRepository.save(userToBlock);
    return "redirect:/admin/users?successblock";
  }

  @GetMapping("/user/block/list")
  public String getBanUsersList(Model model) {
    model.addAttribute("banUsers", userRepository.findAllByisAdminAndEnabled(false, 0));
    return "admin-blocked-users-page";
  }

  @GetMapping("/user/unblock/{id}")
  public String unblockUser(@PathVariable Long id) {
    User userToBlock = userRepository.findById(id).get();
    userToBlock.setEnabled(1);
    userRepository.save(userToBlock);
    return "redirect:/admin/user/block/list?successunblock";
  }

  @GetMapping("/list")
  public String getAdminsList(Model model) {
    List<User> adminsList = userRepository.findAllByisAdminAndEnabled(true, 1);
    model.addAttribute("adminsList", adminsList);
    return "admin-admin-list";
  }

  @GetMapping("/add")
  public String addAdmin(Model model) {
    model.addAttribute("user", new User());
    return "admin-add-page";
  }

  @PostMapping("/add")
  public String processAddAdmin(@Valid @ModelAttribute User user, BindingResult result) {
    if (result.hasErrors()) {
      return "admin-add-page";
    }
    if (!user.getPassword().equals(user.getRePassword())) {
      result.rejectValue("password", "error.user", "Wpisane hasła różnią się od siebie");
      return "admin-add-page";
    }
    userService.saveAdmin(user);
    return "redirect:/admin/list?successadd";
  }

  @GetMapping("/remove/{id}")
  public String removeAdmin(@PathVariable Long id, Principal principal) {
    User currentAdmin = userRepository.findByEmail(principal.getName());
    List<User> adminList = userRepository.findAllByisAdminAndEnabled(true, 1);
    if ((!currentAdmin.getId().equals(id)) && (adminList.size() > 1)) {

      userRepository.delete(userRepository.getOne(id));
      return "redirect:/admin/list?successremove";

    } else {
      return "redirect:/admin/list?error";
    }
  }

  @GetMapping("/edit/{id}")
  public String editAdmin(@PathVariable Long id, Model model) {
    User currentAdmin = userRepository.findById(id).get();
    model.addAttribute("fullName", currentAdmin.getFullName());
    model.addAttribute("user", currentAdmin);
    return "admin-edit-page";
  }

  @PostMapping("/edit")
  public String processEditAdmin(
      @Valid @ModelAttribute User user, BindingResult result, HttpSession ses, Model model) {
    if (result.hasErrors()) {
      return "admin-edit-page";
    }
    userService.updateUser(user);
    ses.removeAttribute("fullName");
    model.addAttribute("fullName", user.getFullName());
    return "redirect:/admin/edit/" + user.getId() + "?seccessedit";
  }

  @GetMapping("/edit/password")
  public String editPassword(Model model) {
    model.addAttribute("passwordDTO", new PasswordDTO());
    return "admin-edit-password-page";
  }

  @PostMapping("/edit/password")
  public String processEditPassword(
      @Valid @ModelAttribute PasswordDTO passwordDTO, BindingResult result, HttpSession ses) {
    Long sesID = (Long) ses.getAttribute("id");
    User currentUser = userRepository.findById(sesID).get();
    if (result.hasErrors()) {
      return "admin-edit-password-page";
    }
    if (!passwordDTO.getPassword().equals(passwordDTO.getRePassword())) {
      return "redirect:/admin/edit/password?error1";
    }
    if (userService.editUserPassword(currentUser, passwordDTO)) {
      return "redirect:/admin/edit/password?success";
    } else {
      return "redirect:/admin/edit/password?error2";
    }
  }

  @GetMapping("/donations")
  public String getDonationsList(Model model) {
    List<Donation> donations = donationRepository.findAll();
    model.addAttribute("donations", donations);
    return "admin-donation-list";
  }

  @GetMapping("/donation/{id}")
  public String getDonationDetails(@PathVariable Long id, Model model) {
    Donation currentDonation = donationRepository.findById(id).get();
    List<Status> statusList = statusRepository.findAll();
    model.addAttribute("statusList", statusList);
    model.addAttribute("donation", currentDonation);
    return "admin-donation-details";
  }

  @PostMapping("/donation/{id}")
  public String changeDonationStatus(
      @RequestParam(name = "status", required = false) Status status, @PathVariable Long id) {
    Donation currentDonation = donationRepository.findById(id).get();
    currentDonation.setStatus(status);
    donationRepository.save(currentDonation);
    return "redirect:/admin/donation/" + currentDonation.getId();
  }
}
