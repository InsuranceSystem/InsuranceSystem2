package aplus.insurancesystem2.domain.customer.entity.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EGender {
    male("남"),
    female("여");

    private final String genderStr;
}