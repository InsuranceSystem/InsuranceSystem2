package aplus.insurancesystem.domain.compensationClaim.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateCarAccidentRequest {
    private final Long insuranceId;
    private final Long customerId;

    private final String receptionistName;
    private final String receptionistPNumber;
    private final String relationshipOfContractor;
    private final String documentFilePath;
    private final String bank;
    private final String accountNumber;
    private final String accountHolderName;

    private final String type;
    private final LocalDateTime dateTime;
    private final String place;
    private final String carNumber;
    private final String driverName;
    private final String licenseNumber;
    private final String accidentDetail;
}
