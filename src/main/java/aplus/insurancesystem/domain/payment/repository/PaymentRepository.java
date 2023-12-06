package aplus.insurancesystem.domain.payment.repository;

import aplus.insurancesystem.domain.payment.entity.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.contract.id = :contractId AND p.dateOfPayment <= CURRENT_DATE")
    List<Payment> findByContractId(@RequestParam("contractId") Long contractId);
}
