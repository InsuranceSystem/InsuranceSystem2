package aplus.insurancesystem.domain.contract.exception;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

public class ContractNotFoundException extends BusinessException {

    public ContractNotFoundException() {
        super(ErrorCode.CONTRACT_NOT_FOUND);
    }
}
