package com.example.readingnoteapi.repository;

import com.example.readingnoteapi.entity.Book;
import com.example.readingnoteapi.entity.enums.Genre;
import com.example.readingnoteapi.entity.enums.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 제목으로 책 검색 (부분 일치, 대소문자 무시)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // 저자로 책 검색
    List<Book> findByAuthor(String author);

    // 별점으로 필터링
    List<Book> findByRatingGreaterThanEqual(Integer rating);

    // 장르별 조회
    List<Book> findByGenre(Genre genre);

    // 상태별 조회
    List<Book> findByStatus(ReadingStatus status);

    // 최식 완독순 정렬
    List<Book> findAllByOrderByFinishedDateDesc();

    // 높은 평점순 정렬
    List<Book> findAllByOrderByRatingDescCreatedAtDesc();

    // 장르별 평균 별점 계산 (JPQL 사용)
    @Query("SELECT AVG(b.rating) FROM Book b WHERE b.genre = :genre")
    Double findAverageRatingByGenre(@Param("genre") Genre genre);

    // 상태별 책 개수 카운트
    long countByStatus(ReadingStatus status);
}
