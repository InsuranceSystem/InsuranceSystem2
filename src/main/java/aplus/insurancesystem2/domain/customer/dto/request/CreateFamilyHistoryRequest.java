package aplus.insurancesystem2.domain.customer.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "가족력 생성 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateFamilyHistoryRequest {

    private final String diseaseName;
    private final String relationship;
}
