package kr.co.udid.ciderpay.model.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizKind
{
    BLOG("Blog사업자"),
    MALL("쇼핑몰사업자"),
    VISIT("방문판매"),
    DELIVERY("음식점(배달)"),
    URGENT("A/S긴급출동"),
    TRANSPORT("운수업"),
    CONTENTS("컨텐츠"),
    SALES("도소매"),
    DISTRIBUTION("유통"),
    HOSPITAL("병원")
    ;

    private final String name;
}
