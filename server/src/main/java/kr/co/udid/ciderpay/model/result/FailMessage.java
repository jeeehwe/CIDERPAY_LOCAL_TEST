package kr.co.udid.ciderpay.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailMessage {

    /*에러 코드*/
    private String errCode;
    /*에러 메시지*/
    private String message;

}
