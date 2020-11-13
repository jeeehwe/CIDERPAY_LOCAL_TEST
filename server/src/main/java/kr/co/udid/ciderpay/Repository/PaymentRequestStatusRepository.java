package kr.co.udid.ciderpay.Repository;

import kr.co.udid.ciderpay.model.PaymentRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestStatusRepository extends JpaRepository<PaymentRequestStatus, Long> {
}
