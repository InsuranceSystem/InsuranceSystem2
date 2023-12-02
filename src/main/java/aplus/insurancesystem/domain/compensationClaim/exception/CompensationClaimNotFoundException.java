package aplus.insurancesystem.domain.compensationClaim.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class CompensationClaimNotFoundException extends BusinessException {
    public CompensationClaimNotFoundException() {
        super(ErrorCode.COMPENSATION_CLIAM_NOT_FOUND);
    }

}
