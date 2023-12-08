package aplus.insurancesystem.domain.Insurance.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class InsuranceNotFoundException extends BusinessException {
    public InsuranceNotFoundException() {
        super(ErrorCode.INSURANCE_NOT_FOUND);
    }
}

