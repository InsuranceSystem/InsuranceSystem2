package aplus.insurancesystem.domain.customer.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class CustomerNotFoundException extends BusinessException {
    public CustomerNotFoundException() {
        super(ErrorCode.CUSTOMER_NOT_FOUND);
    }
}
