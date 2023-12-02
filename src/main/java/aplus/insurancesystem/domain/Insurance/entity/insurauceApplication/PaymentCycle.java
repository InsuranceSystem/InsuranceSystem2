package aplus.insurancesystem.domain.Insurance.entity.insurauceApplication;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentCycle {
    @JsonProperty("월납")
    MONTHLY_PAYMENT("월납"),

    @JsonProperty("분기납")
    QUARTERLY_PAYMENT("분기납"),

    @JsonProperty("반기납")
    SEMI_ANNUAL_PAYMENT("반기납"),

    @JsonProperty("연납")
    ANNUAL_PAYMENT("연납");

    private final String name;
}
