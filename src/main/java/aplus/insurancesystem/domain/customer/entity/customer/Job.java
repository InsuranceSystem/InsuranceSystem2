package aplus.insurancesystem.domain.customer.entity.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Job {

    @JsonProperty("학생")
    STUDENT("학생", 0.1f),
    @JsonProperty("회사원")
    EMPLOYEE("회사원", 0.0f),
    @JsonProperty("교사/교수")
    TEACHER("교사/교수", 0.0f),
    @JsonProperty("전문직 종사")
    SPECIALIZED("전문직 종사", -0.04f),
    @JsonProperty("가정주부")
    HOUSEWIFE("가정주부", 0.05f),
    @JsonProperty("프리랜서")
    FREELANCER("프리랜서", 0.05f),
    @JsonProperty("자영업자")
    SELF_EMPLOYED("자영업자", 0.03f),
    @JsonProperty("예술가")
    ARTIST("예술가", 0.04f),
    @JsonProperty("기타")
    ETC("기타", 0.1f);

    private final String name;
    private final float rate;

    public float getAdditionalRate() {
        return rate;
    }
}
