package com.mycompany.mvcproject.service;

import com.mycompany.mvcproject.domain.QuizQuestion;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class QuizSession {
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<QuizQuestion> questions = new ArrayList<>();

    public QuizSession(){
        // 예시 문제 추가
        questions.add(new QuizQuestion("프랑스의 수도는?", "파리"));
        questions.add(new QuizQuestion("2+2=?", "4"));
        questions.add(new QuizQuestion("'햄릿'의 작가는?", "셰익스피어"));
    }

    // 현재 문제 return해주는 메서드
    public QuizQuestion getCurrentQuestion(){
        return questions.get(currentQuestionIndex);
    }

    // 답을 입력 받는 메서드 -> 점수 count / 문제 이동
    public void answerCurrentQuestion(String answer){
        if (questions.get(currentQuestionIndex).getAnswer().equals(answer)){
            score++;
        }
        currentQuestionIndex++;
    }

    // 마지막 문제인지 체크
    public boolean isQuizFinished(){
        return currentQuestionIndex >= questions.size();
    }

    public int getScore(){
        return score;
    }

    public int getTotalQuestions(){
        return questions.size();
    }

    public void resetQuiz(){
        currentQuestionIndex = 0;
        score = 0;
    }

}
