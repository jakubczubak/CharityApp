package pl.czubak.charityapp.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.config.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
