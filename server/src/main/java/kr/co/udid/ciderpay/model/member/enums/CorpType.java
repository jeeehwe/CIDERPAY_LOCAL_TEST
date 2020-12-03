package kr.co.udid.ciderpay.model.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  CorpType
{
    PERSONAL("1", "개인 사업자"),
    CORPERATE("2", "법인 사업자")
    ;

    private final String name;
    private final String type;

}
