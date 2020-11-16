package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.PaymentFeedback;
import kr.co.udid.ciderpay.model.PaymentRequest;

import java.util.List;

public interface PaymentSv {
    PaymentRequest insertTestData (PaymentRequest request);

    List<PaymentRequest> findAll ();

    PaymentRequest getByPayUrl (String payUrl);

    String makeRandom ();

    PaymentFeedback request (PaymentRequest request);

}
