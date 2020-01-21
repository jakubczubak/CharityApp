package pl.czubak.charityapp.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.config.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution,Long> {
}
