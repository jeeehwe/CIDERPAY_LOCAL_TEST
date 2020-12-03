package kr.co.udid.ciderpay.model.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType
{
    PERSONAL("1"),
    COMPANY("2");

    private final String name;
}
