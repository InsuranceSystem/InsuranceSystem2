package aplus.insurancesystem2.domain.contract.repository;


import aplus.insurancesystem2.domain.contract.domain.Contract;
import aplus.insurancesystem2.domain.contract.domain.ContractId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, ContractId> {

}
