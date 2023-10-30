package aplus.insurancesystem2.Insurance.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class termsCreateRequest {
    @NotBlank
    private String termsID;

    private String termsName;
    private String calculatedMoneyMethod;
    private String termsContent;
}
