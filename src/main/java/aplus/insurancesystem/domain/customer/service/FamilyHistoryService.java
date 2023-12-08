package aplus.insurancesystem.domain.customer.service;

import java.util.List;

import aplus.insurancesystem.domain.customer.dto.request.CreateFamilyHistoryRequest;
import aplus.insurancesystem.domain.customer.dto.response.FamilyHistoryInfoResponse;
import aplus.insurancesystem.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;

public interface FamilyHistoryService {
    List<FamilyHistory> getFamilyHistoryList(Customer customer);

    List<FamilyHistoryInfoResponse> getFamilyHistoryList(Long customerId);

    void createFamilyHistory(Customer customer, CreateFamilyHistoryRequest request);
}
