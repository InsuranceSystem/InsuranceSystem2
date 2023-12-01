package aplus.insurancesystem2.domain.customer.entity.customer;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    public static boolean contains(String roleName) {
        return Arrays.stream(Role.values())
                     .anyMatch(role -> role.getName().equals(roleName));
    }

    public static Role find(String roleName) {
        return Arrays.stream(Role.values())
                     .filter(role -> role.getName().equals(roleName))
                     .findFirst()
                     .orElse(null);
    }
}
