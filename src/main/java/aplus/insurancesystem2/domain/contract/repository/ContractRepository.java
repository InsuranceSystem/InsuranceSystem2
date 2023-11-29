package aplus.insurancesystem2.domain.contract.repository;

<<<<<<< HEAD

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.contract.entity.Contract;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAllByCustomer(Customer customer);
    List<Contract> findAllByInsurance(Insurance insurance);

    Optional<Contract> findAllByContractAndInsurance(Customer customer, Insurance insurance);

}
