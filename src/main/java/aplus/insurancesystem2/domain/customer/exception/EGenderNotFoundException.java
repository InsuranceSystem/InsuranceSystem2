package aplus.insurancesystem2.domain.customer.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class EGenderNotFoundException extends BusinessException {

    public EGenderNotFoundException() {
        super(ErrorCode.EGENDER_NOT_FOUND);
    }
}
