package com.example.simple_todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TodoConroller {

    private final TodoService todoService;

    @Autowired
    public TodoConroller(TodoService todoService) {
        this.todoService = todoService;
    }

    // 루트 경로를 /todos로 리디렉트
    // http://localhost:8080
    @GetMapping("/")
    public String home(){
        return "redirect:/todos";
    }

    // 메인 페이지 - 목록 조회
    @GetMapping("/todos")
    public String listTodos(Model model){
        model.addAttribute("todos", todoService.findAll());
        model.addAttribute("totalCount", todoService.getTotalCount());
        model.addAttribute("completedCount", todoService.getCompletedCount());
        model.addAttribute("pendingCount", todoService.getPendingCount());
        return "todos"; // view페이지
    }

    // 새 할 일 추가

    // 완료 상태 토클

    // 할일 삭제
    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes){
        todoService.deleteTodo(id);
        redirectAttributes.addFlashAttribute("message","할 일이 삭제되었습니다.");
        return "redirect:/todos";
    }
}
