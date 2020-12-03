package kr.co.udid.ciderpay.model.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FailMessage {

    /*에러 코드*/
    private String errCode;
    /*에러 메시지*/
    private String message;

}
