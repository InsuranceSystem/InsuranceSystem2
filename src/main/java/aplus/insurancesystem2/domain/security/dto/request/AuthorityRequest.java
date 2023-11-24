package aplus.insurancesystem2.domain.security.dto.request;

import lombok.Getter;

@Getter
public class AuthorityRequest {
    private String customerId; // 사용자 로그인 id
    private String roleName;
}
