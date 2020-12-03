package kr.co.udid.ciderpay.repository;

import kr.co.udid.ciderpay.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderNoAndToken(String orderNo, String token);
}
