package kr.co.udid.ciderpay.repository;

import kr.co.udid.ciderpay.model.PaymentFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentFeedbackRepository extends JpaRepository<PaymentFeedback, Long> {
}
