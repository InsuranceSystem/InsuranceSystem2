package aplus.insurancesystem2.domain.Insurance.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class InsuranceApplicationNotFoundException extends BusinessException {
    public InsuranceApplicationNotFoundException() {
        super(ErrorCode.INSURANCE_APPLICATION_NOT_FOUND);
    }
}

