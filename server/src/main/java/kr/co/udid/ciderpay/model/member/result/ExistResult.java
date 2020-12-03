package kr.co.udid.ciderpay.model.member.result;

import kr.co.udid.ciderpay.model.common.result.ResultMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExistResult extends ResultMessage
{
    /*
    * 존재 여부
    * Y -> 아이디 존재
    * N -> 존재하지 않음
    * E -> 에러
    * */
    private String exist;
}
