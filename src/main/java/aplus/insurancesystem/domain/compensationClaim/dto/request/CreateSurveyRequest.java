package aplus.insurancesystem.domain.compensationClaim.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateSurveyRequest {

//    private final Long id; //ccid
    private final String managerName;
    private final MultipartFile reportFile;
    private final Integer surveyFee;
    private final Integer decisionMoney;
    private final Boolean responsibility;
    private final String responsibilityReason;

}
