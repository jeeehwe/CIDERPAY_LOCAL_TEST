package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.exception.ProcessStatusException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

public interface PaymentRequestSv extends JpaService<PaymentRequest>
{
    PaymentRequest insertTestData (PaymentRequest request) throws ConstraintViolationException, DataIntegrityViolationException;

    PaymentRequest getByPayUrl (String payUrl);

    PaymentRequest request (PaymentRequest request);

    void feedbackWork(PaymentRequest request);

    PaymentRequest requestCancel(PaymentRequest request) throws ProcessStatusException, NoDataException;
}
