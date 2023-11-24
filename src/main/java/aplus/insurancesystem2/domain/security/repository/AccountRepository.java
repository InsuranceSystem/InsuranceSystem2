package aplus.insurancesystem2.domain.security.repository;

import aplus.insurancesystem2.domain.security.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
