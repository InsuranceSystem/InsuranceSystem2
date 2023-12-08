package aplus.insurancesystem.domain.compensationClaim.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;

@Getter
@Schema(description = "손해사정 보고서 조회 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ReportFilePathResponse {

    private final InputStreamResource reportFileInputStreamResource;

    public static ReportFilePathResponse of(InputStreamResource survey) {
        return new ReportFilePathResponse(survey);
    }
}
