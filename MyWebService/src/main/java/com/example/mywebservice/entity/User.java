package com.example.mywebservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// 사용자 엔티티 - Spring Security UserDetails 구현
// setter 지양, 명시적 생성자. 불변성 보상
@Entity
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Builder
    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // 명시적 업데이트 메서드 (setter 지양)
    public void updatePassword(String encodedPassword){
        this.password = encodedPassword;
    }

    public void updateName(String name){
        this.name = name;
    }


    //------------------------------------------------
    // Spring Security UserDetails 인터페이스 구현
    //------------------------------------------------

    // 사용자 권한 목록 반환
    // 단일 권한 : ROLE_USER 를 기본 부여
    // 추후 관리자 권한(Role.ADMIN 등) 확장
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 로그인 식별자로 사용할 username 필드 지정(이메일)
    @Override
    public String getUsername() {
        return email;
    }


    /*
    *  계정 상태 관련 플래그
    * true: 모두 정상 계정으로 간주
    * 필요시 휴면/정지/만료 정책에 맞게 수정 가능
    * */
    @Override
    public boolean isAccountNonExpired() { return true;  }

    @Override
    public boolean isAccountNonLocked(){ return true;  }

    @Override
    public boolean isCredentialsNonExpired() { return true;  }

    @Override
    public boolean isEnabled() { return true;  }

}
