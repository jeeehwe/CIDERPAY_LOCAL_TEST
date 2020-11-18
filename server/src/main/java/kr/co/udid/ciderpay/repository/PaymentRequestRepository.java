package kr.co.udid.ciderpay.repository;

import kr.co.udid.ciderpay.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
    PaymentRequest findByPayUrl(String payUrl);

    PaymentRequest findByMemberIDAndPayUniqueNo(String memberID, String payUniqueNo);
}
