package aplus.insurancesystem2.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode.getMessage(), throwable);
        this.errorCode = errorCode;
    }

}