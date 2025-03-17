package fr.xwj860725.gestiontaches.models;

import jakarta.persistence.*;

@Entity  // Marked as JPA entity
@Table(name = "tasks") // PostgreSQL table name
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing primary key
    private Long id;

    @Column(nullable = false) // cannot be empty
    private String title;
    private String description;

    private boolean completed; // whether or not the task has been completed

    // function constructor
    public Task() {}

    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getter and Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
