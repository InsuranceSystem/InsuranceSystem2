package aplus.insurancesystem.domain.compensationClaim.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
@Schema(description = "손해사정 수정 Request")
public class UpdateSurveyRequest {

    private final String managerName;
    private final MultipartFile reportFile;
    private final Integer surveyFee;
    private final Integer decisionMoney;
    private final Boolean responsibility;
    private final String responsibilityReason;

}
