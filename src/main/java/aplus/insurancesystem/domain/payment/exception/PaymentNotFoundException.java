package aplus.insurancesystem.domain.payment.exception;


import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class PaymentNotFoundException extends BusinessException {

    public PaymentNotFoundException() {
        super(ErrorCode.PAYMENT_NOT_FOUND);
    }
}
