package aplus.insurancesystem2.domain.compensationClaim.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateCompensationClaimRequest {
    private final Long insuranceId;
    private final Long customerId;

    private final String receptionistName;
    private final String receptionistPNumber;
    private final String relationshipOfContractor;
    private final String documentFilePath;
    private final String bank;
    private final String accountNumber;
    private final String accountHolderName;

}
