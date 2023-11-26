package aplus.insurancesystem2.domain.security.dto.request;

import lombok.Getter;

@Getter
public class FamilyHistoryDto {
    private String customerId;
    private String diseaseName;
    private String relationship;
}
