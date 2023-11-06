package aplus.insurancesystem2.domain.Insurance.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class InsuranceNotFoundException extends BusinessException {
    public InsuranceNotFoundException() {
        super(ErrorCode.INSURANCE_NOT_FOUND);
    }
}

