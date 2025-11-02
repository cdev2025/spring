package com.example.question_board.controller;

import com.example.question_board.dto.request.ReplyRequest;
import com.example.question_board.dto.response.ReplyResponse;
import com.example.question_board.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 댓글(답변) 관리 API
 */
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "댓글 등록", description = "게시글 ID를 지정하여 댓글을 등록합니다.")
    @PostMapping
    public ResponseEntity<ReplyResponse> createReply(
            @Parameter(description = "게시글 ID", required = true)
            @RequestParam("postId") Long postId,
            @Valid @RequestBody ReplyRequest request
    ) {
        ReplyResponse response = replyService.createReply(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "댓글 목록 조회", description = "게시글 ID를 이용해 해당 게시글의 댓글 전체를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<ReplyResponse>> getRepliesByPost(
            @Parameter(description = "게시글 ID", required = true)
            @RequestParam("postId") Long postId
    ) {
        List<ReplyResponse> response = replyService.getRepliesByPost(postId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "댓글 삭제", description = "댓글 ID와 비밀번호를 이용하여 댓글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(
            @Parameter(description = "댓글 ID", required = true)
            @PathVariable("id") Long replyId,
            @RequestBody Map<String, String> request
    ) {
        String password = request.get("password");
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        replyService.deleteReply(replyId, password);
        return ResponseEntity.noContent().build();
    }
}
