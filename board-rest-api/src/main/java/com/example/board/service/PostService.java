package com.example.board.service;

import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 게시글 비즈니스 로직 처리 서비스
// 생성자 주입 / 트랜잭션 관리 적용
@Service
@RequiredArgsConstructor // final 필드 생성자 자동 생성
@Transactional
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    // 새로운 게시글 생성
    public PostResponseDto createPost(PostRequestDto postRequestDto){
        log.info("Creating new post: {}", postRequestDto.getTitle());
        Post post = postRequestDto.toEntity();
        Post savedPost = postRepository.save(post);

        log.debug("Post created with Auditing info - createdAt: {}, createdBy: {}", savedPost.getCreatedAt(), savedPost.getCreatedBy());

        return PostResponseDto.from(savedPost);
    }

    // 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts(){
        log.info("Fetching all posts:");
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }

    // ID로 게시글 조회
    @Transactional(readOnly = true)
    public Optional<PostResponseDto> getPostById(Long id){
        log.info("Fetching post with id: {}", id);
        return postRepository.findById(id)
                .map(PostResponseDto::from);
    }

    // 게시글 수정
    public PostResponseDto updatePost(Long id, PostRequestDto updatePostRequestDto){
        log.info("Updating post with id: {}", id);
        return postRepository.findById(id)
                .map(post -> {
                        post.update(
                                updatePostRequestDto.getTitle(),
                                updatePostRequestDto.getContent(),
                                updatePostRequestDto.getAuthor()
                        );

                        // 트랜잭션 커밋 시 JPA Auditing이 updatedAt, updatedBy 자동 업데이트
                        log.debug("Post updated - will trigger JPA Auditing on transaction commit");

                        return PostResponseDto.from(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found with id: "));
    }

    // 게시글 삭제
    public boolean deletePost(Long id){
        log.info("Deleting post with id: {}", id);
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 키워드 게시글 검색 : JQPL 사용 메서드 호출
    @Transactional(readOnly = true)
    public List<PostResponseDto> searchPostsByKeyword(String keyword){
        log.info("Searching posts by keyword: {}", keyword);
        return postRepository.findByTitleOrContentContaining(keyword)
                .stream()
                .map(PostResponseDto::from)
                .collect(Collectors.toList());
    }
}
