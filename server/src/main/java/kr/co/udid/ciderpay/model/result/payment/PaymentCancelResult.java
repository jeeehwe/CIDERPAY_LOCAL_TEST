package kr.co.udid.ciderpay.model.result.payment;

import kr.co.udid.ciderpay.model.result.ResultMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentCancelResult extends ResultMessage {

    /*실패 시 추가 응답값 - 결제 취소 실패 시 취소 요청이 필요한 경우 "CANCEL_REQ" 로 설정*/
    private String var1;

}
