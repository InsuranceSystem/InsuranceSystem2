package aplus.insurancesystem.domain.customer.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class TermsNotFoundException extends BusinessException {
    public TermsNotFoundException() {
        super(ErrorCode.TERMS_NOT_FOUND);
    }
}
