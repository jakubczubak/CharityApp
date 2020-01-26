package pl.czubak.charityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.czubak.charityapp.entity.Institution;
import pl.czubak.charityapp.entity.User;
import pl.czubak.charityapp.repository.InstitutionRepository;
import pl.czubak.charityapp.repository.UserRepository;
import pl.czubak.charityapp.service.DonationService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("fullName")
public class AdminController {

    private UserRepository userRepository;
    private DonationService donationService;
    private InstitutionRepository institutionRepository;
    public AdminController(UserRepository userRepository, DonationService donationService, InstitutionRepository institutionRepository){
        this.userRepository=userRepository;
        this.donationService=donationService;
        this.institutionRepository=institutionRepository;
    }
    @GetMapping
    public String getAdminPage(Model model, Principal principal){
        User currentUser = userRepository.findByEmail(principal.getName());
        model.addAttribute("fullName", currentUser.getFullName());
        model.addAttribute("donationAmount", donationService.donationAmount());
        model.addAttribute("AmountOfGoodPeople", donationService.numberOfGoodPeople());
        model.addAttribute("AmountOfTrustedInstitution",institutionRepository.findAll().size());
        model.addAttribute("AmountOfAdmins", userRepository.findAllByisAdmin(true).size());
        return "adminPage";
    }

    @GetMapping("/institutions")
    public String getInstitutionsList(Model model){
        List<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions",institutions);
        return "admin-institutionList";
    }

    @GetMapping("/institution/remove/{id}")
    public String deleteInstitutionByID(@PathVariable Long id){
        institutionRepository.delete(institutionRepository.getOne(id));
        return "redirect:/admin/institutions";
    }

    @GetMapping("/institution/add")
        public String addInstitution(Model model){
        model.addAttribute("institution", new Institution());
            return "institution-add-page";
        }

     @PostMapping("/institution/add")
    public String processAddInstitution(@ModelAttribute Institution institution){
        institutionRepository.save(institution);
         return "redirect:/admin/institutions";

     }

     @GetMapping("/institution/edit/{id}")
    public String editInstitution(@PathVariable Long id, Model model){
        Institution currentInstitution = institutionRepository.findById(id).get();
        model.addAttribute("institution", currentInstitution);
         return "institution-edit-page";
     }

    @PostMapping("/institution/edit/")
    public String processEditInstitution(@ModelAttribute Institution institution){
        institutionRepository.save(institution);
        return "redirect:/admin/institutions";
    }
}
