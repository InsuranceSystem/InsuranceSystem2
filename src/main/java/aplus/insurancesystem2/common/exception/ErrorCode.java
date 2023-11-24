package aplus.insurancesystem2.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "서버에 오류가 발생하였습니다."),

    // Customer
    USER_NOT_FOUND(404, "U001", "고객을 찾을 수 없습니다."),
    EGENDER_NOT_FOUND(404, "U002", "성별을 찾을 수 없습니다."),
    ROLE_NOT_FOUND(404, "U003", "권한을 찾을 수 없습니다."),

    // Insurance
    INSURANCE_NOT_FOUND(404, "I001", "보험을 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}
