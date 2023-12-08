package aplus.insurancesystem.common.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Esms {
    COMPENSATION_CLAIM_COMPLETED("보상금 청구가 완료되었습니다. 손해사정에는 수일이 걸릴 수 있습니다."),

    SURVEY_COMPLETED("손해사정이 완료되었습니다. 홈페이지에서 결과를 확인해주세요."),

    INSURANCE_APPLICATION_APPROVED("보험 가입 심사가 완료되었습니다. 홈페이지에서 결과를 확인해주세요.");

    private final String message;
}
