package aplus.insurancesystem2.domain.customer.entity.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EGender {
    @JsonProperty("남")
    male("남"),

    @JsonProperty("여")
    female("여");

    private final String genderStr;
}
