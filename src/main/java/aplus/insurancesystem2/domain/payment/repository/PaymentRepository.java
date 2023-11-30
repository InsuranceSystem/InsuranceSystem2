package aplus.insurancesystem2.domain.payment.repository;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.customer.id = :customerId")
    List<Payment> findByCustomerId(@Param("customerId") long customerId);
}
