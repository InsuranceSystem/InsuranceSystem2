package aplus.insurancesystem2.domain.security.controller;

import aplus.insurancesystem2.domain.security.dto.AuthorizationRequest;
import aplus.insurancesystem2.domain.security.dto.JoinRequest;
import aplus.insurancesystem2.domain.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // 회원 가입
    @PostMapping("/join")
    public HttpStatus join(JoinRequest request) {
        boolean result = accountService.join(request);
        if (result) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // 권한 변경 : 화면 x, 단순 api 이용해서 권한 변경하려는 용도
    @PostMapping("/authorize")
    public HttpStatus changeAuthorization(AuthorizationRequest request) {
        boolean result = accountService.changeAuthorization(request);
        if(result) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
