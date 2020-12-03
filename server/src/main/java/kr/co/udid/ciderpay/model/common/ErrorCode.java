package kr.co.udid.ciderpay.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode
{
    /*
    * Payment
    * */

    /* 취소 상태 에러 */
    ERROR_CANCEL_STATE("2001"),

    /* 토큰 취소 에러 */
    ERROR_CANCEL_TOKEN("2002"),


    /*
    * Member
    * */

    /* 회원 등록 에러 */
    ERRROR_REGIST_MEMBER("1090")
    ;

    private String errorCode;
}
