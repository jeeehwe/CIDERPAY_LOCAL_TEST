package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.member.Member;
import kr.co.udid.ciderpay.model.member.data.MemberRegisterData;
import kr.co.udid.ciderpay.model.common.exception.ExistDataException;
import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.common.exception.NotSetRequiredDataException;

public interface MemberSv
{
    boolean existMemberID (String memberID, String parentMemberID) throws NoDataException;

    String regist (MemberRegisterData data) throws ExistDataException, NoDataException, NotSetRequiredDataException;

    Member getMember (String id);
}
