package aplus.insurancesystem.domain.compensationClaim.dto.response;

import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CompensationClaimResponse {

    private final Long id;
    private final String receptionistName;
    private final String receptionistPNumber;
    private final String relationshipOfContractor;
    private final String documentFilePath;
    private final String bank;
    private final String accountNumber;
    private final String accountHolderName;
    private final boolean isSurveyed;

    private final String insuranceName;


    public static CompensationClaimResponse of (CompensationClaim compensationClaim, String insuranceName) {
        return new CompensationClaimResponse(
                compensationClaim.getId(),
                compensationClaim.getReceptionistName(),
                compensationClaim.getReceptionistPNumber(),
                compensationClaim.getRelationshipOfContractor(),
                compensationClaim.getDocumentFilePath(),
                compensationClaim.getBank(),
                compensationClaim.getAccountNumber(),
                compensationClaim.getAccountHolderName(),
                compensationClaim.isSurveyed(),
                insuranceName
        );
    }
}
