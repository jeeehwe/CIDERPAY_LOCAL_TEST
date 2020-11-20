package kr.co.udid.ciderpay.model.result.payment;

import kr.co.udid.ciderpay.model.PaymentRequest;
import lombok.*;

import javax.persistence.*;

@Data
public class PaymentRequestSuccess {

    /*성공 여부*/
    private boolean success;
    /*결제 가능한 URL*/
    private String payUrl;
    /*결제 고유 아이디*/
    private String payUniqueNo;

}
