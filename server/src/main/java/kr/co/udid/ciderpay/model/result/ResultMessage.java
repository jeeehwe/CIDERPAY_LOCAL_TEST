package kr.co.udid.ciderpay.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage {

    /*성공 여부*/
    private boolean success;
    /*에러 메시지*/
    private String errorMessage;
}
