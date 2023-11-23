package aplus.insurancesystem2.domain.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.customer.entity.FamilyHistory;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.repository.FamilyHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FamilyHistoryServiceImpl implements FamilyHistoryService{

    private final FamilyHistoryRepository familyHistoryRepository;

    @Override
    public List<FamilyHistory> getFamilyHistories(Customer customer) {
        return familyHistoryRepository.findAllByCustomer(customer);
    }
}
