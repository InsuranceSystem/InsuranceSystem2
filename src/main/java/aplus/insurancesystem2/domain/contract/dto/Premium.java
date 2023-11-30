package aplus.insurancesystem2.domain.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Premium {

    private Integer premium;

    public static Premium of(Integer premium) {
        return new Premium(premium);
    }
}
