package aplus.insurancesystem2.domain.contract.repository;

import aplus.insurancesystem2.domain.contract.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
