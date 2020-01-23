package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
