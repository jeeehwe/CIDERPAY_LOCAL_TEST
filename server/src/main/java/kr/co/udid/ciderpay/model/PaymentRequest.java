package kr.co.udid.ciderpay.model;

import kr.co.udid.ciderpay.model.enums.SmsUse;
import kr.co.udid.ciderpay.model.enums.Status;
import kr.co.udid.ciderpay.model.enums.PaymentState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="payment_request")
public class PaymentRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false) private Long requestId;

    @Column(name = "member_id") private String memberID;

    @Column(name = "price") private int price;

    @Column(name = "tax_price") private int taxPrice;

    @Column(name = "tax_free_price") private int taxFreePrice;

    @Column(name = "good_name") private String goodName;

    @Column(name = "mobile")  private String mobile;

    @Column(name = "custom_name")  private String customName;

    @Column(name = "custom_no")  private String customNo;

    @Column(name = "email")  private String email;

    @Column(name = "feedbackurl")  private String feedbackurl;

    @Column(name = "returnurl")  private String returnurl;

    @Column(name = "returnmode")  private String returnmode;

    @Column(name = "var1")  private String var1;

    @Column(name = "var2")  private String var2;

    @Column(name = "smsuse")  private SmsUse smsuse = SmsUse.N;

    @Column(name = "where_from")  private String whereFrom;

    @Column(name = "seller_memo")  private String sellerMemo;

    @Column(name = "create_date") private String createDate;

    @Column(name = "status") private Status status;

    @Column(name = "pay_url") private String payUrl;

    @Column(name = "seller_name") private String sellerName;

    @Column(name = "payment_state") private PaymentState paymentState;

}
