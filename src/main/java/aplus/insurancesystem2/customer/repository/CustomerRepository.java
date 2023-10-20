package aplus.insurancesystem2.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aplus.insurancesystem2.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
