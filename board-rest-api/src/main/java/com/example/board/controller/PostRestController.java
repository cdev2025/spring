package com.example.board.controller;

import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
@Tag(name="게시판 REST API", description = "JPA Auditing이 적용된 게시글 CRUD API")
public class PostRestController {

    private final PostService postService;

    // 게시물 생성 - POST /api/posts
    @Operation(
            summary = "게시글 생성", description = "새로운 게시글을 생성합니다. 생성/수정 시간과 사용자 정보가 자동 설정됩니다."
    )
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto){
        log.info("POST /api/posts - Creating post : {}", postRequestDto.getTitle());

        PostResponseDto createdPost = postService.createPost(postRequestDto);

        log.info("Post created successfully with Auditing - ID : {}, CreatedAt : {}, CreatedBy : {}"
                , createdPost.getId(), createdPost.getCreatedAt(), createdPost.getCreatedBy());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // 게시물 목록 조회 - GET /api/posts
    @Operation(summary = "전체 게시글 조회(Auditing 정보 포함", description = "등록된 모든 게시글을 Auditing 정보와 함께 조회합니다")
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        List<PostResponseDto> posts = postService.getAllPosts();

        return ResponseEntity.ok(posts);
    }

    // 게시물 상세 조회 - GET /api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id){
        return postService.getPostById(id)
                .map(post -> {
                    log.info("Found post - CreatedAt: {}, UpdatedAt: {}", post.getCreatedAt(), post.getUpdatedAt());
                    return ResponseEntity.ok(post);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 게시물 수정 - PUT /api/posts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        try{
            PostResponseDto updatePost = postService.updatePost(id, postRequestDto);

            return ResponseEntity.ok(updatePost);
        }catch(RuntimeException e){
            log.error("Post not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 삭제 - DELETE /api/posts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        if ( postService.deletePost(id) ){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
