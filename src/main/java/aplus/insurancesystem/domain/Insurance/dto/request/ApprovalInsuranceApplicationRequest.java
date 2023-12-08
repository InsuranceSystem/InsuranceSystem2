package aplus.insurancesystem.domain.Insurance.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 가입 신청 승인 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ApprovalInsuranceApplicationRequest {

    private final String reasonOfApproval;
}
