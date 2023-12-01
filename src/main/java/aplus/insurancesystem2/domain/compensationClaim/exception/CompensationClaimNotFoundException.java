package aplus.insurancesystem2.domain.compensationClaim.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class CompensationClaimNotFoundException extends BusinessException {
    public CompensationClaimNotFoundException() {
        super(ErrorCode.COMPENSATION_CLIAM_NOT_FOUND);
    }

}
