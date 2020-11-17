package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.service.PaymentSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/p")
public class ViewCt {
    final private PaymentSv paymentSv;

    @GetMapping("/list")
    public List<PaymentRequest> findAll()
    {
        return paymentSv.findAll();
    }

    @GetMapping("/{payUrl}")
    public PaymentRequest paymentView (@PathVariable String payUrl) { return paymentSv.getByPayUrl(payUrl); }

    @PostMapping("/request")
    public PaymentRequest payRequest(@RequestBody PaymentRequest request) {  return paymentSv.request(request); }

}
