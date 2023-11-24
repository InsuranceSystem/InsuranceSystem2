package aplus.insurancesystem2.domain.security.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class RoleNotFoundException extends BusinessException {
    public RoleNotFoundException() {
        super(ErrorCode.ROLE_NOT_FOUND);
    }
}
