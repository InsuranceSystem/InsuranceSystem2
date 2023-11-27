package aplus.insurancesystem2.domain.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerNameAndPnumber(String customerName, String pnumber);

    @Query("select customer from Contract c join c.customer customer where c.resurrection = true")
    List<Customer> findAllExpiredContracts();

    @Query("select customer from Contract c join c.customer customer where c.maturity = true")
    List<Customer> findAllResurrectCandidates();

    @Query("select customer from Payment p join p.customer customer where p.whetherPayment = false")
    List<Customer> findAllUnpaidCustomers();
}
