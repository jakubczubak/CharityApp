package pl.czubak.charityapp.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.czubak.charityapp.config.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation,Long> {

    @Query("SELECT sum(d.quantity) from Donation d")
    int donationAmount();

    @Query("Select count(distinct d.institution.id) from Donation d")
    int numberOfSupportedInstitutions();


}
