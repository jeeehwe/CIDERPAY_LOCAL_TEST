package kr.co.udid.ciderpay.service.impl;

import kr.co.udid.ciderpay.model.payment.Payment;
import kr.co.udid.ciderpay.model.payment.enums.PaymentState;
import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.common.exception.ProcessStatusException;
import kr.co.udid.ciderpay.repository.PaymentRepository;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSvImpl implements PaymentSv
{
    final private PaymentRepository paymentRepository;

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment checkPaymentExist (Payment requiredPayment) throws NoDataException
    {
        Payment payment = paymentRepository.findByOrderNoAndToken(requiredPayment.getOrderNo(), requiredPayment.getToken());

        if (payment == null)
            throw new NoDataException();

        return payment;
    }

    @Override
    public boolean cancelPayment(Payment requiredPayment) throws NoDataException, ProcessStatusException {
        Payment payment = checkPaymentExist(requiredPayment);

        if (payment.getPaymentState() != PaymentState.COMPLETE)
            throw new ProcessStatusException();

        payment.setPaymentState(PaymentState.CANCEL);
        payment.setCancelMessage(requiredPayment.getCancelMessage());

        paymentRepository.save(payment);

        return true;
    }

    @Override
    public boolean cancelRequestAfterAdjust(Payment requiredPayment) throws NoDataException, ProcessStatusException {
        Payment payment = checkPaymentExist(requiredPayment);

        if (payment.getPaymentState() != PaymentState.ADJUST)
            throw new ProcessStatusException();

        payment.setPaymentState(PaymentState.REQUEST_CANCEL_AFTER_ADJUST);
        payment.setCancelMessage(requiredPayment.getCancelMessage());
        payment.setBankInName(requiredPayment.getBankInName());

        paymentRepository.save(payment);

        return true;
    }

    @Override
    public Payment makeAdjust(Payment payment) {
        payment.setPaymentState(PaymentState.ADJUST);

        return paymentRepository.save(payment);
    }
}
