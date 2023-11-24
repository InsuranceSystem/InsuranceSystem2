package aplus.insurancesystem2.domain.security.dto.request;

import lombok.Data;

@Data
public class JoinRequest {
    private String customerId; // 사용자 로그인 id
    private String password;
    //...
}
