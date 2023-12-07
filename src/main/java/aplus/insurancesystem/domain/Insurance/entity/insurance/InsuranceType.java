package aplus.insurancesystem.domain.Insurance.entity.insurance;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InsuranceType {
    @JsonProperty("자동차") CAR("자동차"),
    @JsonProperty("운전자") DRIVER("운전자"),
    @JsonProperty("실손") ACCIDENT("실손"),
    @JsonProperty("자녀") CHILD("자녀"),
    @JsonProperty("건강") HEALTH("건강"),
    @JsonProperty("유병자") ILLNESS("유병자"),
    @JsonProperty("치아") DENTAL("치아"),
    @JsonProperty("화재") FIRE("화재"),
    @JsonProperty("재산") PROPERTY("재산"),
    @JsonProperty("여행/레저") TRAVEL("여행/레저"),
    @JsonProperty("펫") PET("펫");

    private final String name;
}

