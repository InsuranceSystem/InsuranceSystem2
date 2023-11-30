package aplus.insurancesystem2.domain.payment.repository;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    findByContractId(@RequestParam("contractId"))
}
