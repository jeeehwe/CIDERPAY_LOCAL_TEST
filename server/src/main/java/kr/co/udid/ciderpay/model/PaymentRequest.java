package kr.co.udid.ciderpay.model;

import kr.co.udid.ciderpay.model.enums.SmsUse;
import kr.co.udid.ciderpay.model.enums.PaymentState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name="payment_request")
public class PaymentRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false) private Long requestId;

    @Column(name = "member_id", nullable = false) private String memberID;

    @Column(name = "price", nullable = false) private int price;

    @Column(name = "tax_price") private int taxPrice;

    @Column(name = "tax_free_price") private int taxFreePrice;

    @Column(name = "good_name", nullable = false) private String goodName;

    @Column(name = "mobile", nullable = false)  private String mobile;

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

    @Column(name = "pay_url") private String payUrl;

    @Column(name = "seller_name") private String sellerName;

    @Column(name = "payment_state") private PaymentState paymentState;

    @Column(name = "pay_unique_no") private String payUniqueNo;

}
