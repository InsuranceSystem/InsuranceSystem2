package aplus.insurancesystem.domain.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem.domain.customer.dto.request.CreateFamilyHistoryRequest;
import aplus.insurancesystem.domain.customer.dto.response.FamilyHistoryInfoResponse;
import aplus.insurancesystem.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem.domain.customer.entity.customer.Customer;
import aplus.insurancesystem.domain.customer.repository.FamilyHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FamilyHistoryServiceImpl implements FamilyHistoryService{

    private final FamilyHistoryRepository familyHistoryRepository;
    private final CustomerQueryService customerQueryService;

    @Override
    public List<FamilyHistory> getFamilyHistories(Customer customer) {
        return familyHistoryRepository.findAllByCustomer(customer);
    }

    @Override
    public List<FamilyHistoryInfoResponse> getFamilyHistories(Long customerId) {
        return familyHistoryRepository.findAllByCustomer(customerQueryService.getCustomer(customerId))
                .stream()
                .map(FamilyHistoryInfoResponse::of)
                .toList();
    }

    @Override
    @Transactional
    public void createFamilyHistory(Customer customer, CreateFamilyHistoryRequest request) {
        familyHistoryRepository.save(new FamilyHistory(customer,
                                                       request.getDiseaseName(),
                                                       request.getRelationship()));
    }
}
