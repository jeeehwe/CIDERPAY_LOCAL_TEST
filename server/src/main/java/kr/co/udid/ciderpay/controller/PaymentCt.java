package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.PaymentFeedback;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.data.PaymentRequestFail;
import kr.co.udid.ciderpay.model.data.PaymentRequestSuccess;
import kr.co.udid.ciderpay.model.data.RequestResult;
import kr.co.udid.ciderpay.model.enums.Status;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentCt {
    final private PaymentSv paymentSv;

    @PostMapping("/oapi/payment/request")
    public Object paymentRequest (@RequestBody PaymentRequest request)
    {
        RequestResult result = new RequestResult();

        PaymentRequest paymentRequest =  paymentSv.insertTestData(request);

        if (paymentRequest.getStatus() == Status.SUCCESS) {

            PaymentRequestSuccess success = new PaymentRequestSuccess();

            success.setSuccess(true);
            success.setPayUrl("/p/"+paymentRequest.getPayUrl());
            success.setPayUniqueNo(paymentSv.makeRandom());

            result.setResult(success);

        } else {

            PaymentRequestFail fail = new PaymentRequestFail();

            fail.setErrCode("500");
            fail.setMessage("필수 정보를 빠짐없이 입력해주세요");

            result.setResult(fail);
        }

        return result.getResult();
    }

    @PostMapping("/p/request")
    public PaymentFeedback payRequest(@RequestBody PaymentRequest request)
    {
        PaymentFeedback result = paymentSv.request(request);

        return  result;
    }
}
