package aplus.insurancesystem.common.security;

import aplus.insurancesystem.domain.customer.entity.customer.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerInfo {
    private final Long customerId;
    private final Role role;
}
