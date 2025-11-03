package com.example.question_board.controller;

import com.example.question_board.dto.request.DeleteRequest;
import com.example.question_board.dto.request.PostRequest;
import com.example.question_board.dto.response.PostResponse;
import com.example.question_board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글 관리 API
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 등록", description = "게시판 ID를 지정하여 게시글을 등록합니다.")
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @Parameter(description = "게시판 ID", required = true)
            @RequestParam("boardId") Long boardId,
            @Valid @RequestBody PostRequest request
    ) {
        PostResponse response = postService.createPost(boardId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID로 게시글의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(
            @Parameter(description = "게시글 ID", required = true)
            @PathVariable("id") Long postId
    ) {
        PostResponse response = postService.getPost(postId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "게시판별 게시글 목록 조회", description = "특정 게시판의 모든 게시글을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<PostResponse>> getPostsByBoard(
            @Parameter(description = "게시판 ID", required = true)
            @RequestParam("boardId") Long boardId
    ) {
        List<PostResponse> response = postService.getPostsByBoard(boardId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "게시글 검색", description = "제목 또는 내용에 키워드가 포함된 게시글을 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<PostResponse>> searchPosts(
            @Parameter(description = "검색 키워드", required = true)
            @RequestParam("keyword") String keyword
    ) {
        List<PostResponse> response = postService.searchPosts(keyword);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "게시글 수정", description = "게시글 ID를 이용하여 제목과 내용을 수정합니다. 비밀번호 검증이 필요합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @Parameter(description = "게시글 ID", required = true)
            @PathVariable("id") Long postId,
            @Valid @RequestBody PostRequest request
    ) {
        PostResponse response = postService.updatePost(postId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "게시글 삭제", description = "게시글 ID와 비밀번호를 이용하여 게시글을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<Void> deletePost(
            @Valid @RequestBody DeleteRequest request
    ) {
        postService.deletePost(request);
        return ResponseEntity.noContent().build();
    }
}