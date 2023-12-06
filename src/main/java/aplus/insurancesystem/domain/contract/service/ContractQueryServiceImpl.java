package aplus.insurancesystem.domain.contract.service;

import aplus.insurancesystem.domain.contract.exception.ContractNotFoundException;
import aplus.insurancesystem.domain.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContractQueryServiceImpl implements ContractQueryService {

    private final ContractRepository contractRepository;

    @Override
    public Integer getPremium(Long contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow(ContractNotFoundException::new).getPremium();
    }
}
