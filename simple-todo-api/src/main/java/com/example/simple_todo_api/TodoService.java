package com.example.simple_todo_api;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    // Todo List 저장할 저장소
    private final Map<Long, Todo> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public TodoService() {
        // 초기 샘플 데이터
        addTodo("Spring Boot 시작하기");
        addTodo("Thymeleaf 템플릿 연결");
        addTodo("간단한 CRUD 구현");

        // 첫 번째 todo 완료 상태로 설정
        store.get(1L).setCompleted(true);
    }

    // 전체 todo list 확인 : findAll()
    public List<Todo> findAll(){
        return new ArrayList<>(store.values());
    }

    // 하나의 todo만 조회 : findById()
    public Optional<Todo> fineById(Long id){
        return Optional.ofNullable(store.get(id)); // id에 해당하는 값이 없으면, 안전하게 null 반환
    }

    // todo 생성 : addTodo()
    public Todo addTodo(String title){
        if(title==null || title.trim().isEmpty()){
            throw new IllegalArgumentException("제목은 비워둘 수 업습니다.");
        }
        Long id = sequence.incrementAndGet();
        Todo todo = new Todo(id, title.trim());
        store.put(id, todo);
        return todo;
    }

    // todo 수정 : updateTodo()
    public Optional<Todo> updateTodo(Long id, String title, Boolean completed){
        Todo todo = store.get(id);
        if (todo==null) return Optional.empty();

        if(title != null && !title.trim().isEmpty()){
            todo.setTitle(title.trim());
        }
        if(completed!=null){
            todo.setCompleted(completed);
        }
        return  Optional.of(todo);
    }

    // todo 삭제 : deleteTodo()
    public boolean deleteTodo(Long id){
        return store.remove(id) != null ;
    }

    // 완료 여부 토글 : toggleComplete()
    public void toggleComplete(Long id){
        Todo todo = store.get(id);
        if (todo!=null){
            todo.setCompleted(!todo.isCompleted());
        }
    }

    //  통계 정보
    public int getTotalCount() { return store.size(); }
    public long getCompletedCount() { return store.values().stream().filter(Todo::isCompleted).count(); }
    public long getPendingCount() {
        return getTotalCount() - getCompletedCount();
    }
}
