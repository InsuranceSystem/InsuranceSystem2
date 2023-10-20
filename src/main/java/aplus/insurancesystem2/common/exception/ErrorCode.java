package aplus.insurancesystem2.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "U001", "고객을 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}
