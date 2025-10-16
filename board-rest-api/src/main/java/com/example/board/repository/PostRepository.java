package com.example.board.repository;

import com.example.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목으로 검색 (부분 일치, 대소문자 무시)
    List<Post> findByTitleContainingIgnoreCase(String title);

    // 작성자로 검색
    List<Post> findByAuthorContainingIgnoreCase(String author);

    // 제목 또는 내용으로 검색 (JPQL)
    @Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%',:keyword,'%') OR p.content LIKE CONCAT('%',:keyword,'%')")
    List<Post> findByTitleOrContentContaining(@Param("keyword") String keyword);
}
