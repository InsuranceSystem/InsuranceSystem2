package aplus.insurancesystem.common.converter;

import java.util.EnumSet;


import aplus.insurancesystem.domain.customer.entity.customer.EGender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EGenderConverter implements AttributeConverter<EGender, String> {
    @Override
    public String convertToDatabaseColumn(EGender eGender) {
        return eGender.getGenderStr();
    }

    @Override
    public EGender convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(EGender.class).stream()
                .filter(e -> e.getGenderStr().equals(dbData))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
