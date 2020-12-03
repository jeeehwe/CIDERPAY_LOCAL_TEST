package kr.co.udid.ciderpay.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode
{
    ERROR_CANCEL_STATE("2001"),
    ERROR_CANCEL_TOKEN("2002"),
    ERRROR_REGIST_MEMBER("1090")
    ;

    private String errorCode;
}
