package aplus.insurancesystem2.domain.contract.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByCustomer(Customer customer);
}
