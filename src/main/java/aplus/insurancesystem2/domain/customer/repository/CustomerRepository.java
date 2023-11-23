package aplus.insurancesystem2.domain.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aplus.insurancesystem2.domain.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerNameAndPnumber(String customerName, String pnumber);
}
