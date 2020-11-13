package kr.co.udid.ciderpay.model.data;

import kr.co.udid.ciderpay.model.PaymentRequest;
import lombok.*;

import javax.persistence.*;

@Data
public class PaymentRequestFail {

    /*에러 코드*/
    private String errCode;
    /*에러 메시지*/
    private String message;

}
