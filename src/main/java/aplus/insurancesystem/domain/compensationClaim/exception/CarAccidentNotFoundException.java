package aplus.insurancesystem.domain.compensationClaim.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class CarAccidentNotFoundException extends BusinessException {
    public CarAccidentNotFoundException() {
        super(ErrorCode.CAR_ACCIDENT_NOT_FOUND);
    }

}
