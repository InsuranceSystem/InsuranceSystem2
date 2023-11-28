package aplus.insurancesystem2.domain.Insurance.dto.response;

import aplus.insurancesystem2.domain.Insurance.entity.Terms;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 약관 정보 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TermInfoResponse {
    private final Long termsID;
    private final String termsName;
    private final String calculatedMoneyMethod;
    private final String termsContent;
    public static TermInfoResponse of(Terms terms) {
        return new TermInfoResponse(
                terms.getTermsID(),
                terms.getTermsName(),
                terms.getCalculatedMoneyMethod(),
                terms.getTermsContent()
        );
    }
}
