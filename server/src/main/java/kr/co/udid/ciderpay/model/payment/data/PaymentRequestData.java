package kr.co.udid.ciderpay.model.payment.data;

import kr.co.udid.ciderpay.model.payment.enums.SmsUse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRequestData
{
    private String memberID;

    private int price;

    private int taxPrice;

    private int taxFreePrice;

    private String goodName;

    private String mobile;

    private String customName;

    private String customNo;

    private String email;

    private String feedbackurl;

    private String returnurl;

    private String returnmode;

    private String var1;

    private String var2;

    private SmsUse smsuse = SmsUse.N;

    private String whereFrom;

    private String sellerMemo;
}
