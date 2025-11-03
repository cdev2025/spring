package com.example.question_board.service;

import com.example.question_board.dto.request.DeleteRequest;
import com.example.question_board.dto.request.PostRequest;
import com.example.question_board.dto.response.PostResponse;
import com.example.question_board.entity.Post;
import com.example.question_board.entity.status.PostStatus;
import com.example.question_board.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 게시글 등록
     */
    @Transactional
    public PostResponse createPost(Long boardId, PostRequest request) {
        Post post = request.toEntity(boardId);

        // 비밀번호 암호화
        post = Post.builder()
                .boardId(post.getBoardId())
                .userName(post.getUserName())
                .password(passwordEncoder.encode(post.getPassword()))
                .email(post.getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .status(PostStatus.PUBLIC)
                .build();

        Post savedPost = postRepository.save(post);
        return PostResponse.fromEntity(savedPost);
    }

    /**
     * 게시판별 게시글 전체 조회
     */
    public List<PostResponse> getPostsByBoard(Long boardId) {
        return postRepository.findByBoardId(boardId).stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 게시글 상세 조회
     */
    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return PostResponse.fromEntity(post);
    }

    /**
     * 게시글 검색 (제목 또는 내용)
     */
    public List<PostResponse> searchPosts(String keyword) {
        return postRepository.searchByKeyword(keyword).stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public PostResponse updatePost(Long postId, PostRequest request) {
        Post post = findPostById(postId);
        verifyPassword(request.getPassword(), post.getPassword());

        post = Post.builder()
                .id(post.getId())
                .boardId(post.getBoardId())
                .userName(post.getUserName())
                .password(post.getPassword()) // 기존 비밀번호 유지
                .email(request.getEmail() != null ? request.getEmail() : post.getEmail())
                .title(request.getTitle())
                .content(request.getContent())
                .status(PostStatus.PUBLIC)
                .build();

        Post updated = postRepository.save(post);
        return PostResponse.fromEntity(updated);
    }

    /**
     * 게시글 삭제 (Soft Delete)
     */
    @Transactional
    public void deletePost(DeleteRequest request) {
        Post post = findPostById(request.getId());
        verifyPassword(request.getPassword(), post.getPassword());
        post.changeStatus(PostStatus.DELETED);
    }

    // ==============================
    // 🔸 Private Helper Methods
    // ==============================

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다. (id=" + postId + ")"));
    }

    private void verifyPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}