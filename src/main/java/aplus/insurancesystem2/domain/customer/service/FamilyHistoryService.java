package aplus.insurancesystem2.domain.customer.service;

import java.util.List;

import aplus.insurancesystem2.domain.customer.dto.response.FamilyHistoryInfoResponse;
import aplus.insurancesystem2.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;

public interface FamilyHistoryService {
    List<FamilyHistory> getFamilyHistories(Customer customer);

    List<FamilyHistoryInfoResponse> getFamilyHistories(Long customerId);
}
