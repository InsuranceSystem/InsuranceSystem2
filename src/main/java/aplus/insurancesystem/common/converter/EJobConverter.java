package aplus.insurancesystem.common.converter;

import java.util.EnumSet;

import aplus.insurancesystem.domain.customer.entity.customer.Job;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EJobConverter implements AttributeConverter<Job, String>  {

    @Override
    public String convertToDatabaseColumn(Job job) {
        return job.getName();
    }

    @Override
    public Job convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Job.class).stream()
                      .filter(e -> e.getName().equals(dbData))
                      .findAny()
                      .orElseThrow(IllegalArgumentException::new);
    }
}
