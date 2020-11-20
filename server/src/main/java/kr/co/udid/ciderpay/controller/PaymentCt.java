package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.Payment;
import kr.co.udid.ciderpay.model.result.payment.PaymentCancelResult;
import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.result.payment.PaymentRequestSuccess;
import kr.co.udid.ciderpay.model.result.CommonResult;
import kr.co.udid.ciderpay.model.exception.ProcessStatusException;
import kr.co.udid.ciderpay.model.result.FailMessage;
import kr.co.udid.ciderpay.model.result.ResultMessage;
import kr.co.udid.ciderpay.service.PaymentRequestSv;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/oapi/payment")
public class PaymentCt {
    final private PaymentRequestSv paymentRequestSv;
    final private PaymentSv paymentSv;

    @PostMapping("/request")
    public Object paymentRequest (@RequestBody PaymentRequest request)
    {
        CommonResult result = new CommonResult();

        try
        {
            PaymentRequest paymentRequest =  paymentRequestSv.insertTestData(request);

            PaymentRequestSuccess success = new PaymentRequestSuccess();

            success.setSuccess(true);
            success.setPayUrl("http://localhost:3000/p/"+paymentRequest.getPayUrl());
            success.setPayUniqueNo(paymentRequest.getPayUniqueNo());

            result.setResult(success);
        }
        catch (ConstraintViolationException e)
        {
            result.setResult(new FailMessage("500", "price는 1000 이상이어야 합니다."));
        }
        catch (DataIntegrityViolationException e)
        {
            result.setResult(new FailMessage("500", "필수 정보를 빠짐없이 입력해주세요."));
        }

        return result.getResult();
    }

    @PostMapping("/request/cancel")
    public ResultMessage paymentRequestCancel(@RequestBody PaymentRequest request)
    {
        try
        {
            paymentRequestSv.requestCancel(request);
            
            return new ResultMessage(true, "요청이 취소 되었습니다.");
        }
        catch (NoDataException e)
        {
            return new ResultMessage(false, "존재하지 않는 결제 요청입니다.");
        }
        catch (ProcessStatusException e)
        {
            return new ResultMessage(false, "요청 취소 가능 상태가 아닙니다.");
        }
    }

    @PostMapping("/cancel")
    public Object paymentCancel (@RequestBody Payment payment)
    {
        CommonResult result = new CommonResult();

        try
        {
            boolean success = paymentSv.cancelPayment(payment);

            PaymentCancelResult cancelResult = new PaymentCancelResult();

            cancelResult.setSuccess(success);
            cancelResult.setErrorMessage("취소되었습니다");

            result.setResult(cancelResult);
        }
        catch (ProcessStatusException e)
        {
            result.setResult(new FailMessage("2001", "ERROR_CANCEL_STATE"));
        }
        catch (NoDataException e)
        {
            result.setResult(new FailMessage("2002", "ERROR_CANCEL_TOKEN"));
        }

        return result.getResult();
    }

    @PostMapping("/cancelRequest")
    public Object cancelRequestAfterAdjust (@RequestBody Payment payment)
    {
        CommonResult result = new CommonResult();

        try
        {
            boolean success = paymentSv.cancelRequestAfterAdjust(payment);

            result.setResult(new ResultMessage(success, "결제 취소가 요청되었습니다."));

        }
        catch (ProcessStatusException e)
        {
            result.setResult(new FailMessage("2001", "ERROR_CANCEL_STATE"));
        }
        catch (NoDataException e)
        {
            result.setResult(new FailMessage("2002", "ERROR_CANCEL_TOKEN"));
        }

        return result.getResult();
    }

}
