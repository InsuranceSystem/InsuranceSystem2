package aplus.insurancesystem2.domain.customer.entity.customer;

import aplus.insurancesystem2.domain.security.exception.RoleNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private String name;

    public static boolean contains(String roleName) {
        return Arrays.stream(Role.values())
                .anyMatch(role -> role.getName().equals(roleName));
    }

    public static Role find(String roleName) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getName().equals(roleName))
                .findFirst()
                .orElseThrow(RoleNotFoundException::new);
    }
}
