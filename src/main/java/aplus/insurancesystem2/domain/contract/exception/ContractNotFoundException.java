package aplus.insurancesystem2.domain.contract.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class ContractNotFoundException extends BusinessException {

    public ContractNotFoundException() {
        super(ErrorCode.CONTRACT_NOT_FOUND);
    }
}
