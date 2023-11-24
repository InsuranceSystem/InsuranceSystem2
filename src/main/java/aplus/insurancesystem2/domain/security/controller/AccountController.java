package aplus.insurancesystem2.domain.security.controller;

import aplus.insurancesystem2.common.dto.SuccessResponse;
import aplus.insurancesystem2.domain.security.dto.request.AuthorityRequest;
import aplus.insurancesystem2.domain.security.dto.request.JoinRequest;
import aplus.insurancesystem2.domain.security.dto.response.JoinResponse;
import aplus.insurancesystem2.domain.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/join")
    public ResponseEntity<SuccessResponse<JoinResponse>> join(JoinRequest request) {
        return SuccessResponse.of(
                accountService.join(request)
        ).asHttp(HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public HttpStatus changeAuthority(AuthorityRequest request) {
        accountService.changeAuthority(request);
        return HttpStatus.OK;
    }
}
