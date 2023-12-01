package aplus.insurancesystem2.domain.customer.dto.request;

import aplus.insurancesystem2.domain.customer.entity.customer.EGender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "고객 정보 수정 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CustomerUpdateRequest {

    @Schema(description = "고객 이름")
    private final String customerName;
    @Schema(description = "고객 성별")
    private final EGender customerGender;
    @Schema(description = "고객 생년월일. yyyy-mm-dd 형식으로 입력해주세요.")
    private final String customerBirth;
    @Schema(description = "고객 핸드폰 번호")
    private final String customerPnumber;
    @Schema(description = "고객 주소")
    private final String customerAddress;
    @Schema(description = "고객 직업")
    private final String customerJob;

}
