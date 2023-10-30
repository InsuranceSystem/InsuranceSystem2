package aplus.insurancesystem2.Insurance.dto.response;

import aplus.insurancesystem2.Insurance.domain.Insurance;
import aplus.insurancesystem2.Insurance.domain.Terms;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class termsInfoResponse {
    private final String termsID;
    private final String termsName;
    private final String calculatedMoneyMethod;
    private final String termsContent;
    public static termsInfoResponse of(Terms terms) {
        return new termsInfoResponse(
                terms.getTermsID(),
                terms.getTermsName(),
                terms.getCalculatedMoneyMethod(),
                terms.getTermsContent()
        );
    }
}
