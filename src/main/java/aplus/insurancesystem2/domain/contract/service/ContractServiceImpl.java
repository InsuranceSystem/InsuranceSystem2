package aplus.insurancesystem2.domain.contract.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.contract.repository.ContractRepository;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public List<Contract> getContracts(Customer customer) {
        return contractRepository.findAllByCustomer(customer);
    }
}
