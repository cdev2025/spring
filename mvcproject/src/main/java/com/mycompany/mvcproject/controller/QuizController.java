package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.service.QuizSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    @Autowired
    private QuizSession quizSession;

    // 퀴즈 시작 페이지
    // http://localhost:8080/quiz
    @GetMapping("/quiz")
    public String startQuiz(Model model){

        if(quizSession.isQuizFinished()){
            return "redirect:/quiz-result";
        }

        model.addAttribute("question", quizSession.getCurrentQuestion().getQuestion());
        return "quiz";
    }

    // 퀴즈 제출
    @PostMapping("/quiz")
    public String submitAnswer(@RequestParam String answer){
        quizSession.answerCurrentQuestion(answer);
        return "redirect:/quiz";
    }

    // 퀴즈 결과 페이지
    @GetMapping("/quiz-result")
    public String quizResult(Model model){
        model.addAttribute("score", quizSession.getScore());
        model.addAttribute("total", quizSession.getTotalQuestions());
        return "quizResult";
    }

    // 퀴즈 재시작
    @GetMapping("/restart")
    public String restartQuiz() {
        quizSession.resetQuiz();
        return "redirect:/quiz";
    }
}
