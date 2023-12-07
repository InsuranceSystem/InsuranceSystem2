package aplus.insurancesystem.domain.compensationClaim.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Schema(description = "보상금청구 및 사고접수 Request")
public class CreateCarAccidentRequest {
    private final Long contractId;

    private final String receptionistName;
    private final String receptionistPNumber;
    private final String relationshipOfContractor;
    private final MultipartFile documentFile;
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
