package aplus.insurancesystem2.domain.compensationClaim.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class CarAccidentNotFoundException extends BusinessException {
    public CarAccidentNotFoundException() {
        super(ErrorCode.CAR_ACCIDENT_NOT_FOUND);
    }

}
