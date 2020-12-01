package kr.co.udid.ciderpay.controller.view;

import kr.co.udid.ciderpay.model.Payment;
import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.service.PaymentRequestSv;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/p")
public class PaymentViewCt
{
    final private PaymentRequestSv paymentRequestSv;
    final private PaymentSv paymentSv;

    @GetMapping("/list/request")
    public Iterable<PaymentRequest> findAllRequest() {  return paymentRequestSv.findAll(); }

    @GetMapping("/{payUrl}")
    public PaymentRequest paymentView (@PathVariable String payUrl) { return paymentRequestSv.getByPayUrl(payUrl); }

    @PostMapping("/payment")
    public PaymentRequest payRequest(@RequestBody PaymentRequest request) {  return paymentRequestSv.request(request); }

    @GetMapping("/list/payment")
    public Iterable<Payment> findAllPayment() { return paymentSv.findAll(); }

    @PutMapping("/adjust")
    public Payment adjust(@RequestBody Payment payment) { return paymentSv.makeAdjust(payment); }

}
