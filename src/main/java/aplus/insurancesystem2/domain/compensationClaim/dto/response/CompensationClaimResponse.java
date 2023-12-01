package aplus.insurancesystem2.domain.compensationClaim.dto.response;

import aplus.insurancesystem2.domain.compensationClaim.entity.CompensationClaim;
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

    public static CompensationClaimResponse of (CompensationClaim compensationClaim) {
        return new CompensationClaimResponse(
                compensationClaim.getId(),
                compensationClaim.getReceptionistName(),
                compensationClaim.getReceptionistPNumber(),
                compensationClaim.getRelationshipOfContractor(),
                compensationClaim.getDocumentFilePath(),
                compensationClaim.getBank(),
                compensationClaim.getAccountNumber(),
                compensationClaim.getAccountHolderName()
        );
    }
}
