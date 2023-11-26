package aplus.insurancesystem2.domain.payment.repository;

import aplus.insurancesystem2.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
