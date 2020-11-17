package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.PaymentRequest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface PaymentSv {
    PaymentRequest insertTestData (PaymentRequest request) throws ConstraintViolationException, DataIntegrityViolationException;

    List<PaymentRequest> findAll ();

    PaymentRequest getByPayUrl (String payUrl);

    PaymentRequest request (PaymentRequest request);

    void feedbackWork(PaymentRequest request);
}
