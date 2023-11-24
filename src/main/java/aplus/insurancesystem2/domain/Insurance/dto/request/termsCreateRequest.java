package aplus.insurancesystem2.domain.Insurance.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class termsCreateRequest {
    @NotBlank
    private Long termsID;

    private String termsName;
    private String calculatedMoneyMethod;
    private String termsContent;
}
