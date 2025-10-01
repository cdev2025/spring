package com.example.simple_todo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    // Todo List 저장할 저장소
    private final Map<Long, Todo> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public TodoService() {
        // 초기 샘플 데이터

        // 첫 번째 todo 완료 상태로 설정
    }

    // 전체 todo list 확인 : findAll()

    // todo 생성 : addTodo()

    // todo 삭제 : deleteTodo()

    // 완료 여부 토글 : toggleComplete()

    //  통계 정보
    public int getTotalCount() { return store.size(); }
    public int getCompletedCount() { return store.values().stream().filter(Todo::isCompleted).count(); }
    public int getPendingCount() {
        return getTotalCount() - getCompletedCount();
    }
}
