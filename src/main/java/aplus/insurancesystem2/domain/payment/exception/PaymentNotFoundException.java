package aplus.insurancesystem2.domain.payment.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class PaymentNotFoundException extends BusinessException {

    public PaymentNotFoundException() {
        super(ErrorCode.PAYMENT_NOT_FOUND);
    }
}
