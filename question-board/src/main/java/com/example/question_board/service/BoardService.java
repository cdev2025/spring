package com.example.question_board.service;

import com.example.question_board.dto.request.BoardRequest;
import com.example.question_board.dto.response.BoardResponse;
import com.example.question_board.entity.Board;
import com.example.question_board.entity.status.BoardStatus;
import com.example.question_board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시판 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시판 생성
     */
    @Transactional
    public BoardResponse createBoard(BoardRequest request) {
        Board board = request.toEntity();
        Board saved = boardRepository.save(board);
        return BoardResponse.fromEntity(saved);
    }

    /**
     * 전체 게시판 목록 조회
     */
    public List<BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 특정 게시판 조회
     */
    public BoardResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판을 찾을 수 없습니다."));
        return BoardResponse.fromEntity(board);
    }

    /**
     * 게시판 상태 변경 (예: 비활성화, 삭제 등)
     */
    @Transactional
    public BoardResponse changeStatus(Long boardId, BoardStatus status) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판을 찾을 수 없습니다."));
        board = Board.builder()
                .id(board.getId())
                .boardName(board.getBoardName())
                .status(status)
                .build();
        Board updated = boardRepository.save(board);
        return BoardResponse.fromEntity(updated);
    }
}