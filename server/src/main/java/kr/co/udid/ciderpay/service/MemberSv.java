package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.Member;
import kr.co.udid.ciderpay.model.data.MemberRegisterData;
import kr.co.udid.ciderpay.model.exception.ExistDataException;
import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.exception.NotSetRequiredDataException;

public interface MemberSv
{
    boolean existMemberID (String memberID, String parentMemberID) throws NoDataException;

    String regist (MemberRegisterData data) throws ExistDataException, NoDataException, NotSetRequiredDataException;

    Member getMember (String id);
}
