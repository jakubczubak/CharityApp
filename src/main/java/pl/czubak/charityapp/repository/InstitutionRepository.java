package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {}
