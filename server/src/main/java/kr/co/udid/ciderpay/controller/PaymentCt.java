package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.data.PaymentRequestFail;
import kr.co.udid.ciderpay.model.data.PaymentRequestSuccess;
import kr.co.udid.ciderpay.model.data.RequestResult;
import kr.co.udid.ciderpay.model.enums.PaymentState;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/oapi/payment")
public class PaymentCt {
    final private PaymentSv paymentSv;

    @PostMapping("/request")
    public Object paymentRequest (@RequestBody PaymentRequest request)
    {
        RequestResult result = new RequestResult();

        PaymentRequest paymentRequest =  paymentSv.insertTestData(request);

        if (paymentRequest.getPaymentState() == PaymentState.PROGRESS) {

            PaymentRequestSuccess success = new PaymentRequestSuccess();

            success.setSuccess(true);
            success.setPayUrl("http://localhost:3000/p/"+paymentRequest.getPayUrl());
            success.setPayUniqueNo(paymentRequest.getPayUniqueNo());

            result.setResult(success);

        } else {

            PaymentRequestFail fail = new PaymentRequestFail();

            fail.setErrCode("500");
            fail.setMessage("필수 정보를 빠짐없이 입력해주세요");

            result.setResult(fail);
        }

        return result.getResult();
    }

    @PostMapping("/request/cancel")
    public void payCancel(@RequestBody PaymentRequest request)
    {

    }
}
