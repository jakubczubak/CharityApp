package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail (String email);
    List<User> findByAdmin (boolean isAdmin);
}
