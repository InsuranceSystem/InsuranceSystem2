package aplus.insurancesystem2.common.security;

import aplus.insurancesystem2.domain.customer.entity.customer.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerInfo {
    private final Long customerId;
    private final Role role;
}
