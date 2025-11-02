package com.example.question_board.service;

import com.example.question_board.dto.request.ReplyRequest;
import com.example.question_board.dto.response.ReplyResponse;
import com.example.question_board.entity.Reply;
import com.example.question_board.entity.status.ReplyStatus;
import com.example.question_board.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 댓글(답변) 서비스 (페이징 미적용 버전)
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 댓글 등록
     */
    @Transactional
    public ReplyResponse createReply(Long postId, ReplyRequest request) {
        Reply reply = request.toEntity(postId);

        // 비밀번호 암호화
        reply = Reply.builder()
                .postId(reply.getPostId())
                .userName(reply.getUserName())
                .password(passwordEncoder.encode(reply.getPassword()))
                .title(reply.getTitle())
                .content(reply.getContent())
                .status(ReplyStatus.VISIBLE)
                .build();

        Reply saved = replyRepository.save(reply);
        return ReplyResponse.fromEntity(saved);
    }

    /**
     * 게시글에 등록된 댓글 전체 조회
     */
    public List<ReplyResponse> getRepliesByPost(Long postId) {
        return replyRepository.findByPostId(postId).stream()
                .map(ReplyResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 댓글 단건 조회
     */
    public ReplyResponse getReply(Long replyId) {
        Reply reply = findReplyById(replyId);
        return ReplyResponse.fromEntity(reply);
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public ReplyResponse updateReply(Long replyId, ReplyRequest request) {
        Reply reply = findReplyById(replyId);
        verifyPassword(request.getPassword(), reply.getPassword());

        reply = Reply.builder()
                .id(reply.getId())
                .postId(reply.getPostId())
                .userName(reply.getUserName())
                .password(reply.getPassword()) // 기존 비밀번호 유지
                .title(request.getTitle())
                .content(request.getContent())
                .status(ReplyStatus.VISIBLE)
                .build();

        Reply updated = replyRepository.save(reply);
        return ReplyResponse.fromEntity(updated);
    }

    /**
     * 댓글 삭제 (Soft Delete)
     */
    @Transactional
    public void deleteReply(Long replyId, String password) {
        Reply reply = findReplyById(replyId);
        verifyPassword(password, reply.getPassword());

        reply.changeStatus(ReplyStatus.DELETED);
    }

    // ==============================
    // 🔸 Private Helper Methods
    // ==============================

    private Reply findReplyById(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다. (id=" + replyId + ")"));
    }

    private void verifyPassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
