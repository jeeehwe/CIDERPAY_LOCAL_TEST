package kr.co.udid.ciderpay.Repository;

import kr.co.udid.ciderpay.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
}
