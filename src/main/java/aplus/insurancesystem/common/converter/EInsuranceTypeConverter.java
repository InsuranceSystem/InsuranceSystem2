package aplus.insurancesystem.common.converter;

import java.util.EnumSet;

import aplus.insurancesystem.domain.Insurance.entity.insurance.InsuranceType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EInsuranceTypeConverter implements AttributeConverter<InsuranceType, String> {

    @Override
    public String convertToDatabaseColumn(InsuranceType insuranceType) {
        return insuranceType.getName();
    }

    @Override
    public InsuranceType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(InsuranceType.class).stream()
                      .filter(e -> e.getName().equals(dbData))
                      .findAny()
                      .orElseThrow(IllegalArgumentException::new);
    }
}
