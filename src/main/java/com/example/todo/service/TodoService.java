package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Transactional
    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    @Transactional
    public Todo updateTodo(Long id, boolean completed) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(completed);
        return repository.save(todo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}
