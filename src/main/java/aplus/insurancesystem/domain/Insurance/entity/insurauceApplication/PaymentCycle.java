package aplus.insurancesystem.domain.Insurance.entity.insurauceApplication;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentCycle {
    @JsonProperty("월납")
    MONTHLY_PAYMENT("월납", 1, 0.05f),

    @JsonProperty("분기납")
    QUARTERLY_PAYMENT("분기납", 3, 0.03f),

    @JsonProperty("반기납")
    SEMI_ANNUAL_PAYMENT("반기납", 6, 0.02f),

    @JsonProperty("연납")
    ANNUAL_PAYMENT("연납", 12, 0.01f);

    private final String name;
    private final int value;
    private final float rate;

    public float getAdditionalRate() {
        return rate;
    }

    public int getValue() {
        return value;
    }
}
