package aplus.insurancesystem.domain.compensationClaim.dto.response;

import aplus.insurancesystem.domain.Insurance.entity.insurance.InsuranceType;
import aplus.insurancesystem.domain.compensationClaim.entity.CarAccident;
import aplus.insurancesystem.domain.compensationClaim.entity.CompensationClaim;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CarAccidentResponse {
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
    private final InsuranceType insuranceType;

    private final String type;
    private final LocalDateTime dateTime;
    private final String place;
    private final String carNumber;
    private final String driverName;
    private final String licenseNumber;
    private final String accidentDetail;

    public static CarAccidentResponse of (CompensationClaim compensationClaim, String insuranceName, InsuranceType insuranceType, CarAccident carAccident) {
        return new CarAccidentResponse(
                compensationClaim.getId(),
                compensationClaim.getReceptionistName(),
                compensationClaim.getReceptionistPNumber(),
                compensationClaim.getRelationshipOfContractor(),
                compensationClaim.getDocumentFilePath(),
                compensationClaim.getBank(),
                compensationClaim.getAccountNumber(),
                compensationClaim.getAccountHolderName(),
                compensationClaim.isSurveyed(),
                insuranceName,
                insuranceType,
                carAccident.getType(),
                carAccident.getDateTime(),
                carAccident.getPlace(),
                carAccident.getCarNumber(),
                carAccident.getDriverName(),
                carAccident.getLicenseNumber(),
                carAccident.getAccidentDetail()
        );
    }
}
