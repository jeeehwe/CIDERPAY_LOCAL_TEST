package kr.co.udid.ciderpay.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestCancel {

    /* 성공 여부 */
    private Boolean success;
    /* API 응답 메시지 */
    private String errorMessage;

}
