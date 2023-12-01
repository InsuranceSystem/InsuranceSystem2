package aplus.insurancesystem2.domain.compensationClaim.exception;

import aplus.insurancesystem2.common.exception.BusinessException;
import aplus.insurancesystem2.common.exception.ErrorCode;

public class SurveyNotFoundException extends BusinessException {
    public SurveyNotFoundException() {
        super(ErrorCode.SURVEY_NOT_FOUND);
    }

}
