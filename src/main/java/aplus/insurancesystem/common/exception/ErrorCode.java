package aplus.insurancesystem.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "서버에 오류가 발생하였습니다."),
    NOT_LOGIN(401, "C002", "로그인이 필요합니다."),
    UPLOAD_FILE_ERROR(500, "C003", "파일 업로드에 실패하였습니다."),
    DOWNLOAD_FILE_ERROR(500, "C004", "파일 다운로드에 실패하였습니다."),

    // Customer
    CUSTOMER_NOT_FOUND(404, "U001", "고객을 찾을 수 없습니다."),

    // Insurance
    INSURANCE_NOT_FOUND(404, "I001", "보험을 찾을 수 없습니다."),

    // Terms
    TERMS_NOT_FOUND(404, "T001", "약관을 찾을 수 없습니다."),

    // InsuranceApplication
    INSURANCE_APPLICATION_NOT_FOUND(404, "IA001", "보험 신청을 찾을 수 없습니다."),

    // Contract
    CONTRACT_NOT_FOUND(404, "C001", "계약을 찾을 수 없습니다."),

    // Payment
    PAYMENT_NOT_FOUND(404, "P001", "납입을 찾을 수 없습니다."),

    COMPENSATION_CLAIM_NOT_FOUND(404, "CC001" , "청구 내역을 찾을 수 없습니다."),
    CAR_ACCIDENT_NOT_FOUND(404, "CA001" ,"사고 접수 내역을 찾을 수 없습니다." ),
    SURVEY_NOT_FOUND(404, "SV001", "손해사정 내역을 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}
