package com.example.readingnoteapi.service;

import com.example.readingnoteapi.dto.BookDto;
import com.example.readingnoteapi.entity.Book;
import com.example.readingnoteapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 독서 노트(Book) 관련 비즈니스 로직을 담당하는 Service 계층
 * Controller와 Repository 사이에서 핵심 로직을 처리하고,
 * DTO 변환, 검증, 예외 처리 등을 수행합니다.
 **/
@Service
@RequiredArgsConstructor // 생성자 주입 Autowired  (final 필드를 매개변수로 갖는 생성자 생성)
@Slf4j
@Transactional // 클래스 단위 트랜잭션 적용 (기본: read-write)
public class BookService {

    private final BookRepository bookRepository;

    /*
     * 새 독서 노트 등록 : createBook()
     * - 클라이언트로부터 받은 요청 DTO(BookRequest)를 엔티티로 변환 후 DB에 저장
     * - 저장 결과를 응답 DTO(BookResponse)로 반환
     */


    /*
     * 모든 독서 노트 조회 (평점 + 죄신순 정렬) : getAllBooks()
     * - Repository에서 정렬된 결과를 받아 DTO 리스트로 변환
     * - readOnly = true : 읽기 전용 트랜잭션(성능 최적화)
     */

    /*
     * 특정 독서 노트 조회 : getBookById()
     * - ID로 검색 후 존재하지 않으면 예외 발생
     * */

    /*
    * 독서 노트 수정 : updateBook()
    * - ID로 기존 엔티티 조회 후 DTO의 값으로 변경
    * - JPA의 변경 감지(Dirty Checking)에 의해 자동 업데이트되는 필드 포함
    * */

    /*
    * 독서 노트 삭제 : deleteBook()
    * - ID로 책을 조회한 뒤 존재하면 삭제
    * */

    /*
    * 제목 키워드 검색 : searchBooksByTitle()
    * - 대소문자 구분 없이 부분 일치 검색 수행
    * */

    /*
    * 장르별 조회 : getBlooksByGenre()
    * - 특정 Genre 타입의 책 목록 조회
    * */

    /*
    * 고평점(4점 이상) 책 조회 : getHighRatedBooks()
    * - rating >= 4 조건으로 필터링
    * */

    //----------------------------------------
    // 내부 헬퍼 메서드
    //---------------------------------------
    /*
    * ID로 Book 조회 (없으면 예외 발생) : findBookById()
    * - 코드 중복 방지를 위한 내부 메서드
    * */
    private Book findBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("❌ 독서 노트를 찾을 수 없습니다. (ID: " + id + ")"));
    }

    /*
    * Entity -> DTO 변환 메서드 : convertToResponse()
    * - 컨트롤러 응답용 DTO로 변환
    * */
    private BookDto.BookResponse convertToResponse(Book book){
        return new BookDto.BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReview(),
                book.getRating(),
                book.getFinishedDate(),
                book.getGenre(),
                book.getStatus(),
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }
}
