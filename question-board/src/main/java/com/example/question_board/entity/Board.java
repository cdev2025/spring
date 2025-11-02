package com.example.question_board.entity;

import com.example.question_board.entity.status.BoardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=100)
    private String boardName;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

 //   @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
     // == mappedBy 제거, JoinColumn 사용 ==
     // 단방향 관계로 외래키는 Post 테이블의 board_id 컬럼으로 관리됨
     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
     @JoinColumn(name = "board_id")
     @Builder.Default // Builder 생성 시에도 new ArrayList<>() 적용됨
    private List<Post> postList = new ArrayList<>();
}
