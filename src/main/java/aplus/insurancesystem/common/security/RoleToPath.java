package aplus.insurancesystem.common.security;

import static org.springframework.http.HttpMethod.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class RoleToPath {

    private static final String BASE_URL = "/api";

    private static final String CUSTOMER_URL = BASE_URL + "/customers";
    private static final String INSURANCE_URL = BASE_URL + "/insurances";
    private static final String INSURANCE_APPLICATION_URL = BASE_URL + "/insurance-applications";
    private static final String SURVEY_URL = BASE_URL + "/survey";
    private static final String COMPENSATION_CLAIM = BASE_URL + "/compensation-claim";

    @Getter
    @RequiredArgsConstructor
    public enum ADMIN_URL {
        CUSTOMER_GRANT_AUTHORITY(CUSTOMER_URL + "/{id}/admin", GET.name()),
        CUSTOMER_ALL(CUSTOMER_URL + "/all", GET.name()),
        CUSTOMER_CONTRACT_MAINTENANCE(CUSTOMER_URL + "/contract-maintenance", GET.name()),
        INSURANCE_DESIGN(INSURANCE_URL + "/design", POST.name()),
        INSURANCE_REGISTER(INSURANCE_URL + "/{id}/register", POST.name()),
        INSURANCE_MODIFY(INSURANCE_URL + "/{id}", PUT.name()),
        INSURANCE_DELETE(INSURANCE_URL + "/{id}", DELETE.name()),
        SURVEY(SURVEY_URL + "/{ccid}", POST.name()),
        COMPENSATION_CLAIM_ALL(COMPENSATION_CLAIM + "/all", GET.name()),
        INSURANCE_APPLICATION(INSURANCE_APPLICATION_URL, GET.name()),
        INSURANCE_APPLICATION_APPROVAL(INSURANCE_APPLICATION_URL + "/{id}/approval", POST.name()),
        INSURANCE_APPLICATION_REJECTION(INSURANCE_APPLICATION_URL + "/{id}/rejection", POST.name());

        private final String url;
        private final String method;
    }
}
