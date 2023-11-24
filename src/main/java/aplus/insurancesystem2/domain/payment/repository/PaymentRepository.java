package aplus.insurancesystem2.domain.payment.repository;

import aplus.insurancesystem2.domain.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
