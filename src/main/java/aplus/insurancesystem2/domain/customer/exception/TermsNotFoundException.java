package aplus.insurancesystem2.domain.customer.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class TermsNotFoundException extends BusinessException {
    public TermsNotFoundException() {
        super(ErrorCode.TERMS_NOT_FOUND);
    }
}
