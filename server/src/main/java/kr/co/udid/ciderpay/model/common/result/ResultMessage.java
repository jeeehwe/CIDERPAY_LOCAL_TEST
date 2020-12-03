package kr.co.udid.ciderpay.model.common.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage {

    /*성공 여부*/
    private boolean success;
    /*에러 메시지*/
    private String errorMessage;
}
