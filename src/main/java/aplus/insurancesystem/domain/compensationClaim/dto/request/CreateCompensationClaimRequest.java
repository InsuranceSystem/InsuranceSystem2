package aplus.insurancesystem.domain.compensationClaim.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
@Schema(description = "보상금청구 Request")
public class CreateCompensationClaimRequest {

    private final String receptionistName;
    private final String receptionistPNumber;
    private final String relationshipOfContractor;
    private final String bank;
    private final String accountNumber;
    private final String accountHolderName;
    private final MultipartFile documentFile;

}
