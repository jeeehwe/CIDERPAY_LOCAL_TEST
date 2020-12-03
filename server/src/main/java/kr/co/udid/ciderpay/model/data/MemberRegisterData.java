package kr.co.udid.ciderpay.model.data;

import kr.co.udid.ciderpay.model.enums.SellerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberRegisterData
{
    @NotBlank
    private String resellerid;

    @NotNull
    private SellerType seller_type;

    @NotBlank
    private String userid;

    @NotBlank
    private String userpwd;

    @NotBlank
    private String sellername;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    private String homepage;

    @NotBlank
    private String bizkind;

    private String wherefrom;

    private String zipcode;

    private String addr1;

    private String addr2;

    /* 1=개인, 2=사업자 */
    @NotBlank
    private String usertype;

    private String compbank;

    private String compbanknum;

    private String compbankname;

    /*
     * 이하로 usertype = 2 (사업자) 인 경우 필수
     * */

    private String compregno;

    private String compname;

    private String biztype1;

    private String biztype2;

    private String comptel;

    private String ceo_nm;

    /* 1=개인 사업자, 2=법인 사업자 */
    private String corp_type;

    /*
     * 이하로 usertype = 1 (개인) 인 경우 필수
     * */

    private String username;
}
