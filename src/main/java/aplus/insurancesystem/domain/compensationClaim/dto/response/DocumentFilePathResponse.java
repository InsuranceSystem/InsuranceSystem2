package aplus.insurancesystem.domain.compensationClaim.dto.response;

import org.springframework.core.io.InputStreamResource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "청구 서류 조회 Response")
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DocumentFilePathResponse {

    private final InputStreamResource documentFileInputStreamResource;

    public static DocumentFilePathResponse of(InputStreamResource compensationClaim) {
        return new DocumentFilePathResponse(compensationClaim);
    }
}
