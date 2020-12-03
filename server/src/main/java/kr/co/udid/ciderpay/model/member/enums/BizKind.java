package kr.co.udid.ciderpay.model.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizKind
{
    BLOG("1", "Blog사업자"),
    MALL("2", "쇼핑몰사업자"),
    VISIT("3", "방문판매"),
    DELIVERY("4", "음식점(배달)"),
    URGENT("5", "A/S긴급출동"),
    TRANSPORT("6", "운수업"),
    CONTENTS("7", "컨텐츠"),
    SALES("8", "도소매"),
    DISTRIBUTION("9", "유통"),
    HOSPITAL("10", "병원")
    ;

    private final String name;
    private final String type;
}
