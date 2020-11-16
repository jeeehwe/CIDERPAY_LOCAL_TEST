package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.PaymentFeedback;
import kr.co.udid.ciderpay.model.enums.PaymentState;
import kr.co.udid.ciderpay.repository.PaymentFeedbackRepository;
import kr.co.udid.ciderpay.repository.PaymentRequestRepository;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSvImpl implements PaymentSv {
    final private PaymentRequestRepository requestRepository;
    final private PaymentFeedbackRepository feedbackRepository;

    @Override
    public PaymentRequest insertTestData(PaymentRequest request) {

        request.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        request.setSellerName("(주)쏘다");

        if ( request.getMemberID() != null && request.getPrice() >= 1000
            && request.getGoodName() != null && request.getMobile() != null )
        {
            request.setStatus(Status.SUCCESS);
            request.setPayUrl(makeRandom());
            request.setPaymentState(PaymentState.PROCESS);
        }
        else {
            request.setStatus(Status.FAIL);
            request.setPaymentState(PaymentState.FAIL);
        }

        PaymentRequest result = requestRepository.save(request);

        return result;
    }

    @Override
    public List<PaymentRequest> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public PaymentRequest getByPayUrl(String payUrl) {
        PaymentRequest paymentRequest = requestRepository.findByPayUrl(payUrl);

        return paymentRequest;
    }

    @Override
    public String makeRandom() {
        String randomPayUrl = makeShuffle() + makeShuffle() + makeShuffle() + makeShuffle() + makeShuffle() + makeShuffle() + makeShuffle() + makeShuffle();

        return randomPayUrl;
    }

    public String makeShuffle() {
        List<String> makeShuffle = Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "n",
                "m", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

        Collections.shuffle(makeShuffle);

        return makeShuffle.get(0);
    }

    public String makeRandomNum() {
        List<String> makeShuffle = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

        return makeShuffle.get(0) + makeShuffle.get(1) + makeShuffle.get(2) + makeShuffle.get(3) + makeShuffle.get(4) + makeShuffle.get(5) + makeShuffle.get(6);
    }


    @Override
    public PaymentFeedback request(PaymentRequest request) {
        PaymentFeedback feedback = new PaymentFeedback();

        feedback.setMemberID(request.getMemberID());
        feedback.setGoodName(request.getGoodName());
        feedback.setPrice(request.getPrice());
        feedback.setRecvPhone(request.getMobile());
        feedback.setVar1(request.getVar1());
        feedback.setVar2(request.getVar2());

        feedback.setFeedbackToken("feedbackToken");
        feedback.setPaymentState(PaymentState.COMPLETE);
        feedback.setPayType(1); //카드 결제
        feedback.setOrderNo(makeRandomNum());
        feedback.setApprovalNo(makeRandomNum());
        feedback.setCcname("신한카드");
        feedback.setCsturl(makeRandom());

        PaymentFeedback result = feedbackRepository.save(feedback);

        request.setPaymentState(PaymentState.COMPLETE);
        request.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        requestRepository.save(request);

        return result;
    }




}
