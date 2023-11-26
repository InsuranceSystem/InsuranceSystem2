package aplus.insurancesystem2.domain.security.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class JoinRequest {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String job;
    private String gender;
    private String birth;
    private String address;
    private List<FamilyHistory> familyHistoryList;
}
