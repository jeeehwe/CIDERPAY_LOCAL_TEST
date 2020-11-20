package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.Payment;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.service.PaymentRequestSv;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/p")
public class ViewCt {
    final private PaymentRequestSv paymentRequestSv;
    final private PaymentSv paymentSv;

    @GetMapping("/list/request")
    public Iterable<PaymentRequest> findAllRequest() {  return paymentRequestSv.findAll(); }

    @GetMapping("/{payUrl}")
    public PaymentRequest paymentView (@PathVariable String payUrl) { return paymentRequestSv.getByPayUrl(payUrl); }

    @PostMapping("/request")
    public PaymentRequest payRequest(@RequestBody PaymentRequest request) {  return paymentRequestSv.request(request); }

    @GetMapping("/list/payment")
    public Iterable<Payment> findAllPayment() { return paymentSv.findAll(); }

}
