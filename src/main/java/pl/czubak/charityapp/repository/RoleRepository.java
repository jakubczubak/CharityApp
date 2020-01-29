package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByRole(String role);
}
