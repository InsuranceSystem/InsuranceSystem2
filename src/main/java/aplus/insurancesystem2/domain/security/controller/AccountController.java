package aplus.insurancesystem2.domain.security.controller;

import aplus.insurancesystem2.domain.security.dto.request.AuthorityRequest;
import aplus.insurancesystem2.domain.security.dto.request.JoinRequest;
import aplus.insurancesystem2.domain.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/verify/id")
    public HttpStatus verifyId(@RequestParam String customerId) {
        boolean isNormal = accountService.verifyId(customerId);
        return getHttpStatus(isNormal);
    }

    @PostMapping("/join")
    public HttpStatus join(@RequestBody JoinRequest request) {
        System.out.println(0);
        boolean isNormal = accountService.join(request);
        return getHttpStatus(isNormal);
    }

    @PostMapping("/authorize")
    public HttpStatus changeAuthority(@RequestBody AuthorityRequest request) {
        accountService.changeAuthority(request);
        return HttpStatus.OK;
    }

    private HttpStatus getHttpStatus(boolean isNormal) {
        if (isNormal) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
