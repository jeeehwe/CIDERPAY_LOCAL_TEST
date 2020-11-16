package kr.co.udid.ciderpay.model;

import kr.co.udid.ciderpay.model.enums.PaymentState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="payment_feedback")
public class PaymentFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false) private Long feedbackId;

    @Column(name = "member_id", nullable = false) private String memberID;

    @Column(name = "feedback_token", nullable = false) private String feedbackToken;

    @Column(name = "good_name", nullable = false) private String goodName;

    @Column(name = "price", nullable = false) private int price;

    @Column(name = "recv_phone", nullable = false) private String recvPhone;

    @Column(name = "payment_state", nullable = false) private PaymentState paymentState;

    @Column(name = "pay_type", nullable = false) private int payType;

    @Column(name = "order_no", nullable = false) private String orderNo;

    @Column(name = "approval_no", nullable = false) private String approvalNo;

    @Column(name = "ccname", nullable = false) private String ccname;

    @Column(name = "var1")  private String var1;

    @Column(name = "var2")  private String var2;

    @Column(name = "card_num")  private String cardNum;

    @Column(name = "card_quota")   private String cardQuota;

    @Column(name = "csturl", nullable = false) private String csturl;

}
