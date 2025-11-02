package com.example.question_board.repository;

import com.example.question_board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 질문 글 ID로 답변 목록 조회
    // findByPostId => post_id 컬럼으로 조회
    // SELECT * FROM replies WHERE post_id = ?
    List<Reply> findByPostId(Long postId);

}
