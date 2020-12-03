package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.payment.Payment;
import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.common.exception.ProcessStatusException;
import kr.co.udid.ciderpay.service.util.JpaService;

public interface PaymentSv extends JpaService<Payment>
{
    boolean cancelPayment(Payment requiredPayment) throws NoDataException, ProcessStatusException;

    boolean cancelRequestAfterAdjust(Payment requiredPayment) throws NoDataException, ProcessStatusException;

    Payment makeAdjust(Payment payment);
}
