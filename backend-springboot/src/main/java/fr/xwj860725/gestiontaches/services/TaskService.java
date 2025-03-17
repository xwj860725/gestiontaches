package fr.xwj860725.gestiontaches.services;

import fr.xwj860725.gestiontaches.models.Task;
import fr.xwj860725.gestiontaches.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Mark as Spring Service Layer
public class TaskService {
    private final TaskRepository taskRepository;

    // Constructor dependency injection
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get tasks by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Add tasks
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Update tasks
    public Task updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());
            return taskRepository.save(task);
        }).orElse(null); // if the task isn't existed, return null
    }

    // Delete tasks
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    // Method of searching for tasks, fuzzy matching titles and descriptions (ignoring letter case)
    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
}

