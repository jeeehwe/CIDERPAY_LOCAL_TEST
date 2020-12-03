package kr.co.udid.ciderpay.model.payment.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PaymentRequestSuccess {

    /*성공 여부*/
    private boolean success;
    /*결제 가능한 URL*/
    private String payUrl;
    /*결제 고유 아이디*/
    private String payUniqueNo;

}
