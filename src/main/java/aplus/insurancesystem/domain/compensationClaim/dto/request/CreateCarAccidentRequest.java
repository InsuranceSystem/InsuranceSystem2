package aplus.insurancesystem.domain.compensationClaim.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Schema(description = "보상금청구 및 사고접수 Request")
public class CreateCarAccidentRequest {

    private final String receptionistName;
    private final String receptionistPNumber;
    private final String relationshipOfContractor;
    private final MultipartFile documentFile;
    private final String bank;
    private final String accountNumber;
    private final String accountHolderName;

    private final String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime dateTime;
    private final String place;
    private final String carNumber;
    private final String driverName;
    private final String licenseNumber;
    private final String accidentDetail;
}
