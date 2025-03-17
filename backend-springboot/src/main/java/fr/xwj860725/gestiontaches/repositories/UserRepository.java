package fr.xwj860725.gestiontaches.repositories;

import fr.xwj860725.gestiontaches.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // method to search by email
    Optional<User> findByName(String name);  // method to search by user names
}
