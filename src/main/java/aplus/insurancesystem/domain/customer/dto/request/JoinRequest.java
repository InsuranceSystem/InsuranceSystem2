package aplus.insurancesystem.domain.customer.dto.request;

import java.util.List;

import aplus.insurancesystem.domain.customer.entity.customer.EGender;
import aplus.insurancesystem.domain.customer.entity.customer.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "회원가입 Request")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class JoinRequest {
    private final String loginId;
    private final String password;
    private final String name;
    private final String phoneNumber;
    private final Job job;
    private final EGender gender;
    private final String birth;
    private final String address;
    private final List<CreateFamilyHistoryRequest> familyHistoryList;
}