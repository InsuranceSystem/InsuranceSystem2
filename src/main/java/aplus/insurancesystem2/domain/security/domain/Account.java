package aplus.insurancesystem2.domain.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String username; // 사용자 로그인 id

    private String password;

    private Role role;

    // customer 연결 혹은 customer 엔티티와 통합

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.CUSTOMER; // 기본 권한 : 고객
    }

    public void changeRole(Role role) {
        this.role = role;
    }
}
