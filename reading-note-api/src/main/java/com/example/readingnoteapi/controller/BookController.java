package com.example.readingnoteapi.controller;

import com.example.readingnoteapi.dto.BookDto;
import com.example.readingnoteapi.entity.enums.Genre;
import com.example.readingnoteapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 독서 노트 REST API Controller
* 외부에서 독서 노트 API를 호출할 수 있는 엔드포인트를 제공합니다.
* */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;
    
    // 새 독서 노트 등록 : POST /api/books
    @PostMapping
    public ResponseEntity<BookDto.BookResponse> createBook(@Valid @RequestBody BookDto.BookRequest request){
        log.info("POST /api/books - 독서 노트 등록: {}", request.title());
        BookDto.BookResponse response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // 모든 독서 노트 조회 : GET /api/books
    @GetMapping
    public ResponseEntity<List<BookDto.BookResponse>> getAllBooks(){
        log.info("GET /api/books - 전체 독서 노트 조회");
        List<BookDto.BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    
    // 특정 독서 노트 조회 : GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookDto.BookResponse> getBook(@PathVariable Long id){
        log.info("GET /api/books/{} - 독서 노트 조회", id);
        try{
            BookDto.BookResponse book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    // 독서 노트 수정 : PUT /api/books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BookDto.BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDto.BookRequest request
    ){
        log.info("PUT /api/books/{} - 독서 노트 수정", id);
        try{
            BookDto.BookResponse updated = bookService.updateBook(id, request);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    // 독서 노트 삭제 : DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        log.info("DELETE /api/books/{} - 독서 노트 삭제", id);
        try{
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    // 제목으로 검색 : GET /api/books/search?keyword=검색어
    @GetMapping("/search")
    public ResponseEntity<List<BookDto.BookResponse>> searchBooks(@RequestParam String keyword){
        log.info("GET /api/books/search - 검색: '{}'", keyword);

        List<BookDto.BookResponse> books = bookService.searchBooksByTitle(keyword);
        return ResponseEntity.ok(books);
    }
    
    // 장르별 조회 : GET /api/books/genre/{genre}
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDto.BookResponse>> getBooksByGenre(@PathVariable Genre genre){
        log.info("GET /api/books/genre/{} - 장르별 조회", genre);

        List<BookDto.BookResponse> books = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }
    
    // 고평점 독서 노트 조회 : GET /api/books/high-rated
    @GetMapping("/high-rated")
    public ResponseEntity<List<BookDto.BookResponse>> getHighRatedBooks(){
        log.info("GET /api/books/high-rated - 고평점 독서 노트 조회");

        List<BookDto.BookResponse> books = bookService.getHightRatedBooks();
        return ResponseEntity.ok(books);
    }
}
