package com.example.question_board.repository;

import com.example.question_board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 게시판 ID로 질문 목록 조회
    // findByBoardId(Long boardId)
    // -> SELECT * FROM posts WHERE board_id = ? "
    List<Post> findByBoardId(Long boardId);

    // 제목 또는 내용에 키워드 포함 질문 검색
    // JPQL 특징
    // - 테이블명 대신 Entity명 사용 : Post (posts 아님)
    // - 컬럼명 대신 필드명 사용 : p.title (title 컬럼)
    // - :keyword는 파라미터 바인딩
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<Post> searchByKeyword(@Param("keyword") String keyword);

    // Native SQL
    @Query(value = "SELECT * FROM posts WHERE title LIKE %:keyword%", nativeQuery = true)
    List<Post> searchByKeywordNative(@Param("keyword") String keyword);
}
