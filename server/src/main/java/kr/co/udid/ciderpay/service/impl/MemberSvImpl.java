package kr.co.udid.ciderpay.service.impl;

import kr.co.udid.ciderpay.model.member.Member;
import kr.co.udid.ciderpay.model.member.data.MemberRegisterData;
import kr.co.udid.ciderpay.model.member.enums.BizKind;
import kr.co.udid.ciderpay.model.member.enums.UserType;
import kr.co.udid.ciderpay.model.common.exception.ExistDataException;
import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.common.exception.NotSetRequiredDataException;
import kr.co.udid.ciderpay.model.member.UserTypeCompany;
import kr.co.udid.ciderpay.model.member.UserTypePersonal;
import kr.co.udid.ciderpay.repository.MemberRepository;
import kr.co.udid.ciderpay.service.MemberSv;
import kr.co.udid.ciderpay.service.common.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSvImpl implements MemberSv
{
    final private MemberRepository memberRepository;

    @Override
    public boolean existMemberID (String memberID, String parentMemberID) throws NoDataException
    {
        //if (!memberRepository.existsByParentMemberID (parentMemberID))
        //    throw new NoDataException ("상위아이디가 존재하지 않습니다.");

        return memberRepository.existsByUserid (memberID);
    }

    @Override
    public String regist (MemberRegisterData data) throws ExistDataException, NoDataException, NotSetRequiredDataException
    {
        if (memberRepository.existsByUserid (data.getUserid ()))
            throw new ExistDataException ("이미 존재하는 아이디입니다.");

        //if (!memberRepository.existsByParentMemberID (data.getResellerid ()))
        //    throw new NoDataException ("상위아이디가 존재하지 않습니다.");

        if (validateUsertype (data))
        {
            Member member = registMemberCore (data);

            memberRepository.save (member);
        }
        else
            throw new NotSetRequiredDataException ("필수 데이터가 없습니다.");

        return data.getUserid ();
    }

    @Override
    public Member getMember (String userid)
    {
        return memberRepository.findByUserid (userid);
    }

    private Member registMemberCore (MemberRegisterData data) throws NoDataException
    {
        Member member = new Member ();
        BeanUtils.copyProperties (data, member);

        member.setParentMemberID (data.getResellerid ());
        setBizKind (member, data.getBizkind ());
        setUserType (member, data.getUsertype ());

        member = memberRepository.save (member);

        if (member.getUsertype ().equals (UserType.PERSONAL))
        {
            UserTypePersonal type = new UserTypePersonal (data.getUsername ());
            member.setPersonalType (type);
        }
        else {
            UserTypeCompany type = new UserTypeCompany ().setCompregno (data.getCompregno ())
                    .setCompname (data.getCompname ()).setBiztype1 (data.getBiztype1 ()).setBiztype2 (data.getBiztype2 ())
                    .setComptel (data.getComptel ()).setCeo_nm (data.getCeo_nm ()).setCorp_type (data.getCorp_type ());
            member.setCompanyType (type);
        }

        return member;
    }

    private boolean validateUsertype (MemberRegisterData data) throws NoDataException
    {
        // usertype -> 개인일 때
        if (data.getUsertype ().equals ("1")) {
            if (Util.isEmptyStr (data.getUsername ()))
                return false;
        }
        // usertype -> 사업자일 때
        else if (data.getUsertype ().equals ("2"))
        {
            if (Util.isEmptyStr (data.getCompregno ()))
                return false;
            if (Util.isEmptyStr (data.getCompname ()))
                return false;
            if (Util.isEmptyStr (data.getBiztype1 ()))
                return false;
            if (Util.isEmptyStr (data.getBiztype2 ()))
                return false;
            if (Util.isEmptyStr (data.getComptel ()))
                return false;
            if (Util.isEmptyStr (data.getCeo_nm ()))
                return false;
        }
        // usertype 이 잘못되었을 때
        else
            throw new NoDataException ("존재하는 사용자 타입이 아닙니다");

        return true;
    }

    private Member setBizKind (Member member, String bizKind) throws NoDataException
    {
        switch (bizKind)
        {
            case "Blog사업자": member.setBizkind (BizKind.BLOG); break;
            case "쇼핑몰사업자": member.setBizkind (BizKind.MALL); break;
            case "방문판매": member.setBizkind (BizKind.VISIT); break;
            case "음식점(배달)": member.setBizkind (BizKind.DELIVERY); break;
            case "A/S긴급출동": member.setBizkind (BizKind.URGENT); break;
            case "운수업": member.setBizkind (BizKind.TRANSPORT); break;
            case "컨텐츠": member.setBizkind (BizKind.CONTENTS); break;
            case "도소매": member.setBizkind (BizKind.SALES); break;
            case "유통": member.setBizkind (BizKind.DISTRIBUTION); break;
            case "병원": member.setBizkind (BizKind.HOSPITAL); break;
            default: throw new NoDataException ("존재하는 서비스 형태가 아닙니다");
        }

        return member;
    }

    private Member setUserType (Member member, String userType)
    {
        switch (userType)
        {
            case "1": member.setUsertype (UserType.PERSONAL); break;
            case "2": member.setUsertype (UserType.COMPANY); break;
        }

        return member;
    }

}
