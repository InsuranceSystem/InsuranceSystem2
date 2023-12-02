package aplus.insurancesystem.domain.Insurance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 가입 신청 거절 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RejectInsuranceApplicationRequest {

    @Schema(description = "거절 사유")
    private final String reasonOfRejection;
}
