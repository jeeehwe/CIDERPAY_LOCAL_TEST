package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.payment.PaymentRequest;
import kr.co.udid.ciderpay.model.common.exception.ProcessStatusException;
import kr.co.udid.ciderpay.model.payment.data.PaymentRequestData;
import kr.co.udid.ciderpay.service.util.JpaService;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;

public interface PaymentRequestSv extends JpaService<PaymentRequest>
{
    PaymentRequest insertTestData (PaymentRequestData data) throws ConstraintViolationException, DataIntegrityViolationException;

    PaymentRequest getByPayUrl (String payUrl);

    PaymentRequest request (PaymentRequest request);

    void feedbackWork(PaymentRequest request);

    PaymentRequest requestCancel(PaymentRequest request) throws ProcessStatusException, NoDataException;
}
