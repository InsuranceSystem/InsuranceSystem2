package aplus.insurancesystem.domain.customer.dto.response;

import aplus.insurancesystem.domain.customer.entity.FamilyHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "가족력 정보 Response")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FamilyHistoryInfoResponse {

    @Schema(description = "질환명")
    private final String diseaseName;
    @Schema(description = "가족 관계")
    private final String relationship;

    public static FamilyHistoryInfoResponse of(FamilyHistory familyHistory) {
        return new FamilyHistoryInfoResponse(familyHistory.getDiseaseName(), familyHistory.getRelationship());
    }
}
