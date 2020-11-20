package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.Payment;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.exception.ProcessStatusException;

public interface PaymentSv extends JpaService<Payment>
{
    boolean cancelPayment(Payment requiredPayment) throws NoDataException, ProcessStatusException;

    boolean cancelRequestAfterAdjust(Payment requiredPayment) throws NoDataException, ProcessStatusException;
}
