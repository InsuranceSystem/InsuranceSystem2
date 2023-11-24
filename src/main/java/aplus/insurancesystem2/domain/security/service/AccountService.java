package aplus.insurancesystem2.domain.security.service;

import aplus.insurancesystem2.domain.security.domain.Account;
import aplus.insurancesystem2.domain.security.domain.Role;
import aplus.insurancesystem2.domain.security.dto.AuthorizationRequest;
import aplus.insurancesystem2.domain.security.dto.JoinRequest;
import aplus.insurancesystem2.domain.security.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public boolean join(JoinRequest request) {
        Account findAccount = accountRepository.findByUsername(request.getUserName());
        if (findAccount != null) {
            return false; // 이미 존재하는 id
        }
        Account newAccount = new Account(request.getUserName(), request.getPassword());
        accountRepository.save(newAccount);
        // customer도 생성되어야, 혹은 account-customer 통합
        return true; // 정상 동작
    }

    @Transactional
    public boolean changeAuthorization(AuthorizationRequest request) {
        Account findAccount = accountRepository.findByUsername(request.getUserName());
        if (findAccount == null) {
            return false; // account 존재 X
        }
        if (!Role.contains(request.getRoleName())) {
            return false; // 권한 존재 X
        }
        findAccount.changeRole(Role.find(request.getRoleName()));
        return true; // 정상 동작
    }
}
