package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.PaymentFeedback;
import kr.co.udid.ciderpay.model.enums.PaymentState;
import kr.co.udid.ciderpay.repository.PaymentRequestRepository;
import kr.co.udid.ciderpay.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSvImpl implements PaymentSv {
    final private PaymentRequestRepository requestRepository;
    final private Util util;

    @Override
    public PaymentRequest insertTestData(PaymentRequest request)
    {
        request.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        request.setSellerName("(주)쏘다");

        request.setPayUrl(util.makeRandom());
        request.setPaymentState(PaymentState.PROGRESS);
        request.setPayUniqueNo(util.makeRandom() + util.makeRandom());
        request.setFeedbackurl("http://localhost:3000/feedback/"+util.makeRandom());
        request.setReturnurl("http://localhost:3000/return/"+util.makeRandom());

        PaymentRequest result = requestRepository.save(request);

        return result;
    }

    @Override
    public List<PaymentRequest> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public PaymentRequest getByPayUrl(String payUrl)
    {
        PaymentRequest paymentRequest = requestRepository.findByPayUrl(payUrl);

        return paymentRequest;
    }

    @Override
    public PaymentRequest request(PaymentRequest request)
    {
        request.setPaymentState(PaymentState.COMPLETE);
        request.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        PaymentRequest modRequest = requestRepository.save(request);

        feedbackWork(modRequest);

        return modRequest;
    }

    @Override
    public void feedbackWork(PaymentRequest request)
    {
        String feedbackUrl = request.getFeedbackurl();

        if (!util.isEmptyStr(feedbackUrl))
        {
            if (!feedbackUrl.startsWith ("http://") && !feedbackUrl.startsWith ("https://"))
                feedbackUrl = "http://" + feedbackUrl;



        }

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
        feedback.setOrderNo(util.makeRandomNum());
        feedback.setApprovalNo(util.makeRandomNum());
        feedback.setCcname("신한카드");
        feedback.setCsturl(util.makeRandom());

    }


}
