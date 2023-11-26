package aplus.insurancesystem2.domain.security.dto.request;

import lombok.Getter;

@Getter
public class AuthorityRequest {
    private String username;
    private String roleName;
}
