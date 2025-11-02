package com.example.question_board.controller;

import com.example.question_board.dto.request.BoardRequest;
import com.example.question_board.dto.response.BoardResponse;
import com.example.question_board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시판 관리 API
 */
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판 생성", description = "새로운 게시판을 등록합니다.")
    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody @Valid BoardRequest request) {
        BoardResponse response = boardService.createBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "게시판 상세 조회", description = "게시판 ID를 이용해 특정 게시판 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable("id") Long boardId) {
        BoardResponse response = boardService.getBoard(boardId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "전체 게시판 목록 조회", description = "등록된 모든 게시판 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<BoardResponse> response = boardService.getAllBoards();
        return ResponseEntity.ok(response);
    }
}