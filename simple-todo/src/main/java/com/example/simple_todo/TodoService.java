package com.example.simple_todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // todo 삭제 : deleteTodo()
    public void deleteTodo(Long id){
        store.remove(id);
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
