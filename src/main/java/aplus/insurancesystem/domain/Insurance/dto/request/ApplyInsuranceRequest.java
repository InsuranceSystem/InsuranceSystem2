package aplus.insurancesystem.domain.Insurance.dto.request;

import org.springframework.web.multipart.MultipartFile;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.PaymentCycle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 신청 Request")
@RequiredArgsConstructor
public class ApplyInsuranceRequest {

    private final Long customerId;
    private final PaymentCycle paymentCycle;
    private final MultipartFile subscriptionFile;
}
