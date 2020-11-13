package kr.co.udid.ciderpay.server;

import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.PaymentRequestStatus;

import javax.persistence.PersistenceException;
import java.util.List;

public interface PaymentSv {
    void insertTestData (PaymentRequest request) throws Exception;

    List<PaymentRequestStatus> findAll ();

}
