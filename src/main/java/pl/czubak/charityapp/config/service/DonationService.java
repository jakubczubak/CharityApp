package pl.czubak.charityapp.config.service;

import org.springframework.stereotype.Service;
import pl.czubak.charityapp.config.repository.DonationRepository;

@Service
public class DonationService {

    private DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository){
        this.donationRepository=donationRepository;
    }

    public int donationAmount() {

        try {
            return donationRepository.donationAmount();
        } catch (Exception e) {
            return 0;
        }
    }
}
