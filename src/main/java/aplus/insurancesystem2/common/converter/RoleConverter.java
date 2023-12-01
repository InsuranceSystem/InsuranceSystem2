package aplus.insurancesystem2.common.converter;

import aplus.insurancesystem2.domain.customer.entity.customer.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getName();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Role.class).stream()
                .filter(e -> e.getName().equals(dbData))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
