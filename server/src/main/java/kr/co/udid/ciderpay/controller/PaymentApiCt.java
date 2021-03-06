package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.common.ErrorCode;
import kr.co.udid.ciderpay.model.payment.Payment;
import kr.co.udid.ciderpay.model.payment.data.PaymentRequestData;
import kr.co.udid.ciderpay.model.payment.result.PaymentCancelResult;
import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.payment.PaymentRequest;
import kr.co.udid.ciderpay.model.payment.result.PaymentRequestSuccess;
import kr.co.udid.ciderpay.model.common.result.CommonResult;
import kr.co.udid.ciderpay.model.common.exception.ProcessStatusException;
import kr.co.udid.ciderpay.model.common.result.FailMessage;
import kr.co.udid.ciderpay.model.common.result.ResultMessage;
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
public class PaymentApiCt
{
    final private PaymentRequestSv paymentRequestSv;
    final private PaymentSv paymentSv;

    @PostMapping("/request")
    public Object paymentRequest (@RequestBody PaymentRequestData data)
    {
        CommonResult result = new CommonResult ();

        try {
            PaymentRequest paymentRequest = paymentRequestSv.insertTestData (data);

            PaymentRequestSuccess success = new PaymentRequestSuccess ();

            success.setSuccess (true);
            success.setPayUrl ("http://localhost:3000/p/" + paymentRequest.getPayUrl ());
            success.setPayUniqueNo (paymentRequest.getPayUniqueNo ());

            result.setResult (success);
        }
        catch (ConstraintViolationException e) {
            result.setResult (new FailMessage ("", "price는 1000 이상이어야 합니다."));
        }
        catch (DataIntegrityViolationException e) {
            result.setResult (new FailMessage ("", "필수 정보를 빠짐없이 입력해주세요."));
        }

        return result.getResult ();
    }

    @PostMapping("/request/cancel")
    public ResultMessage paymentRequestCancel (@RequestBody PaymentRequest request)
    {
        try {
            paymentRequestSv.requestCancel (request);

            return new ResultMessage (true, "요청이 취소 되었습니다.");
        }
        catch (NoDataException e) {
            return new ResultMessage (false, "존재하지 않는 결제 요청입니다.");
        }
        catch (ProcessStatusException e) {
            return new ResultMessage (false, "요청 취소 가능 상태가 아닙니다.");
        }
    }

    @PostMapping("/cancel")
    public Object paymentCancel (@RequestBody Payment payment)
    {
        CommonResult result = new CommonResult ();

        try {
            boolean success = paymentSv.cancelPayment (payment);

            PaymentCancelResult cancelResult = new PaymentCancelResult ();

            cancelResult.setSuccess (success);
            cancelResult.setErrorMessage ("취소되었습니다");

            result.setResult (cancelResult);
        }
        catch (ProcessStatusException e) {
            result.setResult (new FailMessage (ErrorCode.ERROR_CANCEL_STATE.getErrorCode (), ErrorCode.ERROR_CANCEL_STATE.name ()));
        }
        catch (NoDataException e) {
            result.setResult (new FailMessage (ErrorCode.ERROR_CANCEL_TOKEN.getErrorCode (), ErrorCode.ERROR_CANCEL_TOKEN.name ()));
        }

        return result.getResult ();
    }

    @PostMapping("/cancelRequest")
    public Object cancelRequestAfterAdjust (@RequestBody Payment payment)
    {
        CommonResult result = new CommonResult ();

        try {
            boolean success = paymentSv.cancelRequestAfterAdjust (payment);

            result.setResult (new ResultMessage (success, "정산된 결제 건이 취소되었습니다."));

        }
        catch (ProcessStatusException e) {
            result.setResult (new FailMessage (ErrorCode.ERROR_CANCEL_STATE.getErrorCode (), ErrorCode.ERROR_CANCEL_STATE.name ()));
        }
        catch (NoDataException e) {
            result.setResult (new FailMessage (ErrorCode.ERROR_CANCEL_TOKEN.getErrorCode (), ErrorCode.ERROR_CANCEL_TOKEN.name ()));
        }

        return result.getResult ();
    }

}
