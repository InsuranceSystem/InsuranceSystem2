package aplus.insurancesystem2.customer.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class CustomerNotFoundException extends BusinessException {
    public CustomerNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
