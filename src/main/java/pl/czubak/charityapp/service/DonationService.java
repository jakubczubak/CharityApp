package pl.czubak.charityapp.service;

import org.springframework.stereotype.Service;
import pl.czubak.charityapp.repository.DonationRepository;

@Service
public class DonationService {

    private DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int donationAmount() {

        try {
            return donationRepository.donationAmount();
        } catch (Exception e) {
            return 0;
        }
    }

    public int numberOfGoodPeople() {
        try {
            return donationRepository.numberOfGoodPeople();
        } catch (Exception e) {
            return 0;
        }
    }
}
