package aplus.insurancesystem2.domain.customer.entity.customer;

<<<<<<< HEAD
import aplus.insurancesystem2.domain.customer.exception.EGenderNotFoundException;
import java.util.Arrays;
=======
import com.fasterxml.jackson.annotation.JsonProperty;

>>>>>>> 88576b260b94243ffa6788228039aef3bb2ff71f
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
