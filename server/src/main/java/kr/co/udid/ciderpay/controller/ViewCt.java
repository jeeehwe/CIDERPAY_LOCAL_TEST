package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViewCt {
    final private PaymentSv paymentSv;

    @GetMapping("/list")
    public List<PaymentRequest> findAll() {
        return paymentSv.findAll();
    }

    @GetMapping("/p/{payUrl}")
    public PaymentRequest paymentView (@PathVariable String payUrl) {
        return paymentSv.getByPayUrl(payUrl);
    }

}
