package aplus.insurancesystem.domain.compensationClaim.dto.response;

import aplus.insurancesystem.domain.compensationClaim.entity.CarAccident;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CarAccidentResponse {

    private final String type;
    private final LocalDateTime dateTime;
    private final String place;
    private final String carNumber;
    private final String driverName;
    private final String licenseNumber;
    private final String accidentDetail;

    public static CarAccidentResponse of (CarAccident carAccident) {
        return new CarAccidentResponse(
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
