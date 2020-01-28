package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.entity.Status;

public interface StatusRepository extends JpaRepository<Status,Long> {

    Status findByName (String statusName);
}
