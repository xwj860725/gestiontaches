package fr.xwj860725.gestiontaches.controllers;

import fr.xwj860725.gestiontaches.models.Task;
import fr.xwj860725.gestiontaches.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Search task api, fuzzy match title and description
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam("keyword") String keyword) {
        return taskService.searchTasks(keyword);
    }

     // Add tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.orElseThrow(() -> new RuntimeException("task is not found！"));
    }

     // Modifier tasks
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        if (updatedTask.getTitle() == null || updatedTask.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

     // Delete tasks
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
