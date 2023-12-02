package aplus.insurancesystem.domain.Insurance.dto.response;

import aplus.insurancesystem.domain.Insurance.entity.insurauceApplication.InsuranceApplication;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "보험 상세 조회 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SubscriptionFilePathResponse {

    private final String subscriptionFilePath;

    public static SubscriptionFilePathResponse of(InsuranceApplication insuranceApplication) {
        return new SubscriptionFilePathResponse(insuranceApplication.getSubscriptionFilePath());
    }
}
