package aplus.insurancesystem2.domain.contract.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ContractListResponse {
    private List<ContractListElement> contractInfoList;

    public static ContractListResponse of(List<ContractListElement> contractInfoList) {
        return new ContractListResponse(contractInfoList);
    }
}
