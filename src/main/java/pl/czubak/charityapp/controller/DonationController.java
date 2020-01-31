package pl.czubak.charityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.czubak.charityapp.entity.Category;
import pl.czubak.charityapp.entity.Donation;
import pl.czubak.charityapp.entity.Institution;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.model.Error;
import pl.czubak.charityapp.repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/donation")
@SessionAttributes({"fullName", "id"})
public class DonationController {

  private CategoryRepository categoryRepository;
  private InstitutionRepository institutionRepository;
  private DonationRepository donationRepository;
  private UserRepository userRepository;
  private StatusRepository statusRepository;

  @Autowired
  Validator validator;

  public DonationController(
      CategoryRepository categoryRepository,
      InstitutionRepository institutionRepository,
      DonationRepository donationRepository,
      UserRepository userRepository,
      StatusRepository statusRepository) {
    this.categoryRepository = categoryRepository;
    this.institutionRepository = institutionRepository;
    this.donationRepository = donationRepository;
    this.userRepository = userRepository;
    this.statusRepository = statusRepository;
  }

  @GetMapping
  public String getDonationPage(Model model, Principal principal, HttpServletRequest request) {

    User currentUser = userRepository.findByEmail(principal.getName());
    model.addAttribute("fullName", currentUser.getFullName());
    model.addAttribute("id", currentUser.getId());
    model.addAttribute("donation", new Donation());
    model.addAttribute("categories", categoryRepository.findAll());
    model.addAttribute("institutions", institutionRepository.findAll());
    return "form";
  }

  @PostMapping
  public String processDonation(
      @RequestParam(value = "category", required = false) List<Category> categories,
      @RequestParam(value = "organization", required = false) Institution institution,
      @ModelAttribute Donation donation,
      Principal principal, Model model) {

    //IF WE UPDATE DONATIONS
    //START
    if(donation.getId()!=null){
      Donation currentDonation = donationRepository.findById(donation.getId()).get();
      donation.setCreated(currentDonation.getCreated());
    }
    //FINISH
    User currentUser = userRepository.findByEmail(principal.getName());
    donation.setUser(currentUser);
    donation.setInstitution(institution);
    donation.setCategories(categories);
    donation.setStatus(statusRepository.findByName("Zlozone"));

    Set<ConstraintViolation<Donation>> violations = validator.validate(donation);
    if(!violations.isEmpty()){
      List<Error> errorList = new ArrayList<>();
      for (ConstraintViolation<Donation> constraintViolation : violations) {
        errorList.add(new Error(constraintViolation.getMessage())); }
      model.addAttribute("donationErrorList", errorList);
      return "form-error-list";
    }
    donationRepository.save(donation);
    return "form-confirmation";
  }
}
