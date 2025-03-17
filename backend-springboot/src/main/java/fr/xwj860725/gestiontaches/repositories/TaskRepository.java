package fr.xwj860725.gestiontaches.repositories; 

import fr.xwj860725.gestiontaches.models.Task; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // Specifies this is a data warehouse interface
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Inherit JpaRepository, to make it has basic add, delete, modify, and retrieve functionality
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword, String descKeyword);
}
