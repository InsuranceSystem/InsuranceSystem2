package aplus.insurancesystem.domain.Insurance.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class InsuranceApplicationNotFoundException extends BusinessException {
    public InsuranceApplicationNotFoundException() {
        super(ErrorCode.INSURANCE_APPLICATION_NOT_FOUND);
    }
}

