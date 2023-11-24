package aplus.insurancesystem2.domain.security.dto;

import lombok.Data;

@Data
public class AuthorizationRequest {
    private String userName; // 사용자 로그인 id
    private String roleName;
}
