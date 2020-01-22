package pl.czubak.charityapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.czubak.charityapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
