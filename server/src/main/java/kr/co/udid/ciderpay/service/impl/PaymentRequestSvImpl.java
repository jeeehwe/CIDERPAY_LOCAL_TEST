package kr.co.udid.ciderpay.service.impl;

import kr.co.udid.ciderpay.model.Payment;
import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.enums.PaymentState;
import kr.co.udid.ciderpay.model.exception.ProcessStatusException;
import kr.co.udid.ciderpay.repository.PaymentRepository;
import kr.co.udid.ciderpay.repository.PaymentRequestRepository;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.service.PaymentRequestSv;
import kr.co.udid.ciderpay.service.common.Util;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.apache.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentRequestSvImpl implements PaymentRequestSv
{
    final private PaymentRequestRepository requestRepository;
    final private PaymentRepository paymentRepository;
    final private Util util;

    @Override
    public PaymentRequest insertTestData(PaymentRequest request)
    {
        request.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        request.setPayUrl(util.makeRandomStr());
        request.setPaymentState(PaymentState.REQUEST);
        request.setPayUniqueNo(util.makeRandomStr() + util.makeRandomStr());
        request.setFeedbackurl("http://localhost:8080");
        request.setReturnurl("/");

        PaymentRequest result = requestRepository.save(request);

        return result;
    }

    @Override
    public Iterable<PaymentRequest> findAll() {
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

        if (!Util.isEmptyStr(feedbackUrl))
        {
            if (!feedbackUrl.startsWith("http://") && !feedbackUrl.startsWith("https://"))
                feedbackUrl = "http://" + feedbackUrl;

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(feedbackUrl);

            post.setHeader ("User-Agent", "CiderpayLocalTest Java Module");

            List<NameValuePair> urlParameters = new ArrayList<>();

            String feedbackToken = "feedbackToken";
            String token = "token";
            String orderNo = util.makeRandomNum();
            String approvalNo = util.makeRandomNum();

            Payment payment = new Payment();
            BeanUtils.copyProperties(request, payment);

            payment.setFeedbackToken(feedbackToken);
            payment.setToken(token);
            payment.setOrderNo(orderNo);
            payment.setApprovalNo(approvalNo);
            payment.setPaymentState(PaymentState.COMPLETE);

            urlParameters.add(new BasicNameValuePair("member_id", request.getMemberID()));
            urlParameters.add(new BasicNameValuePair("feedback_token", feedbackToken)); //피드백 토큰 -> 결제 취소 시 필요
            urlParameters.add(new BasicNameValuePair("good_name", request.getGoodName()));
            urlParameters.add(new BasicNameValuePair("price", String.valueOf(request.getPrice())));
            urlParameters.add(new BasicNameValuePair("recv_phone", request.getMobile()));
            urlParameters.add(new BasicNameValuePair("payment_state", request.getPaymentState().name()));
            urlParameters.add(new BasicNameValuePair("pay_type", "1")); //결제 수단 - 카드 결제
            urlParameters.add(new BasicNameValuePair("order_no", orderNo)); //주문번호
            urlParameters.add(new BasicNameValuePair("approval_no", approvalNo)); //승인번호
            urlParameters.add(new BasicNameValuePair("ccname", "신한카드"));
            urlParameters.add(new BasicNameValuePair("var1", request.getVar1()));
            urlParameters.add(new BasicNameValuePair("var2", request.getVar2()));
            urlParameters.add(new BasicNameValuePair("card_num", "1234-****-****-1234"));
            urlParameters.add(new BasicNameValuePair("card_quota", "3")); //할부 개월
            urlParameters.add(new BasicNameValuePair("csturl", util.makeRandomStr())); //영수증 url

            post.setEntity(new UrlEncodedFormEntity(urlParameters, StandardCharsets.UTF_8));

            try
            {
                HttpResponse response = client.execute(post);

                request.setStatusCode(response.getStatusLine().getStatusCode());

                //결제 요청 저장
                requestRepository.save(request);

                //결제 저장
                paymentRepository.save(payment);

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String responseStr;

                StringBuilder result = new StringBuilder();

                while ((responseStr = rd.readLine()) != null)
                {
                    result.append(responseStr).append("\n");
                }

                rd.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    @Override
    public PaymentRequest requestCancel(PaymentRequest request) throws ProcessStatusException, NoDataException
    {
        PaymentRequest paymentRequest = requestRepository.findByMemberIDAndPayUniqueNo(request.getMemberID(), request.getPayUniqueNo());

        if (paymentRequest == null)
            throw new NoDataException();

        if (paymentRequest.getPaymentState() != PaymentState.REQUEST)
            throw new ProcessStatusException();

        paymentRequest.setPaymentState(PaymentState.REQUEST_CANCEL);

        return requestRepository.save(paymentRequest);
    }
}
