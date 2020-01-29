package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.czubak.charityapp.entity.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

  @Query("SELECT sum(d.quantity) from Donation d")
  int donationAmount();

  @Query("Select count(distinct d.institution.id) from Donation d")
  int numberOfSupportedInstitutions();

  @Query("select count (distinct d.user.id) from Donation d")
  int numberOfGoodPeople();

  List<Donation> findAllByisArchivedAndUserId(boolean isArchived, Long UserId);
}
