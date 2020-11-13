package kr.co.udid.ciderpay.server;

import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.data.PaymentRequestFail;
import kr.co.udid.ciderpay.model.data.PaymentRequestSuccess;
import kr.co.udid.ciderpay.model.data.RequestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentCt {
    final private PaymentSv paymentSv;

    @PostMapping("/oapi/payment/request")
    public Object paymentRequest (@RequestBody PaymentRequest request)
    {
        RequestResult result = new RequestResult();

        try {
            paymentSv.insertTestData(request);

            PaymentRequestSuccess success = new PaymentRequestSuccess();
            success.setSuccess(true);
            success.setPayUrl("test");
            success.setPayUniqueNo("no");

            result.setResult(success);
        }
        catch (Exception e)
        {
            PaymentRequestFail fail = new PaymentRequestFail();
            fail.setErrCode("500");
            fail.setMessage("실패했습니다");

            result.setResult(fail);
        }

        return result.getResult();
    }
}
