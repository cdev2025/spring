package com.example.board.service;

import com.example.board.dto.PostDto;
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
    public PostDto createPost(PostDto postDto){
        log.info("Creating new post: {}", postDto.getTitle());
        Post post = postDto.toEntity();
        Post savedPost = postRepository.save(post);
        return PostDto.from(savedPost);
    }

    // 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts(){
        log.info("Fetching all posts:");
        return postRepository.findAll()
                .stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // ID로 게시글 조회
    @Transactional(readOnly = true)
    public Optional<PostDto> getPostById(Long id){
        log.info("Fetching post with id: {}", id);
        return postRepository.findById(id)
                .map(PostDto::from);
    }

    // 게시글 수정
    public PostDto updatePost(Long id, PostDto updatePostDto){
        log.info("Updating post with id: {}", id);
        return postRepository.findById(id)
                .map(post -> {
                        post.update(
                                updatePostDto.getTitle(),
                                updatePostDto.getContent(),
                                updatePostDto.getAuthor()
                        );
                        return PostDto.from(post);
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
    public List<PostDto> searchPostsByKeyword(String keyword){
        log.info("Searching posts by keyword: {}", keyword);
        return postRepository.findByTitleOrContentContaining(keyword)
                .stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }
}
