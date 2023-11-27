package aplus.insurancesystem2.domain.Insurance.dto.request;

import aplus.insurancesystem2.domain.Insurance.entity.Terms;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "새 약관 등록 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateTermsRequest {

    private final String termsName;
    private final String calculatedMoneyMethod;
    private final String termsContent;

    public static CreateTermsRequest of(Terms terms) {
        return new CreateTermsRequest(
                terms.getTermsName(),
                terms.getCalculatedMoneyMethod(),
                terms.getTermsContent()
        );
    }
}

