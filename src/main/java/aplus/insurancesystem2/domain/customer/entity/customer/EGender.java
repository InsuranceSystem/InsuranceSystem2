package aplus.insurancesystem2.domain.customer.entity.customer;

import aplus.insurancesystem2.domain.customer.exception.EGenderNotFoundException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EGender {
    male("남"),
    female("여");

    private final String genderStr;

    public static EGender find(String genderStr) {
        return Arrays.stream(EGender.values())
                .filter(role -> role.getGenderStr().equals(genderStr))
                .findFirst()
                .orElseThrow(EGenderNotFoundException::new);
    }
}
