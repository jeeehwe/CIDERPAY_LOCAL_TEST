package kr.co.udid.ciderpay.server;

import kr.co.udid.ciderpay.model.PaymentRequest;
import kr.co.udid.ciderpay.model.PaymentRequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViewCt {
    final private PaymentSv paymentSv;

    @GetMapping("/list")
    public List<PaymentRequestStatus> findAll() {
        List<PaymentRequestStatus> list =  paymentSv.findAll();

        System.out.println(list);
        return list;
    }

}
