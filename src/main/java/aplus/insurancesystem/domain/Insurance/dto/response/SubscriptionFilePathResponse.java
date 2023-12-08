package aplus.insurancesystem.domain.Insurance.dto.response;

import org.springframework.core.io.InputStreamResource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "청약서 조회 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SubscriptionFilePathResponse {

    private final InputStreamResource subscriptionFileInputStreamResource;

    public static SubscriptionFilePathResponse of(InputStreamResource insuranceApplication) {
        return new SubscriptionFilePathResponse(insuranceApplication);
    }
}
