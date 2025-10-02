package com.example.simple_todo_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // 전체 조회
    // http://localhost:8080/api/todos
    @GetMapping
    public List<Todo> getAllTodos(){
        return service.findAll();
    }

    // 한 건 조회
    // http://localhost:8080/api/todos/1
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        return service.fineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()
                );
    }

    // 생성
    @PostMapping
    // http://localhost:8080/api/todos
    public ResponseEntity<Todo> createTodo(@RequestBody Map<String, String> request){
        try{
            String title = request.get("title");
            Todo created = service.addTodo(title);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    // 수정
    // http://localhost:8080/api/todos/1
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,
                                           @RequestBody Map<String, Object> request){
        String title = (String)request.get("title");
        Boolean completed = (Boolean) request.get("completed");

        return service.updateTodo(id, title, completed)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deeleteTodo(@PathVariable Long id){
        if( service.deleteTodo(id) ) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
