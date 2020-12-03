package kr.co.udid.ciderpay.service;

import kr.co.udid.ciderpay.model.Member;
import kr.co.udid.ciderpay.model.data.MemberRegisterData;
import kr.co.udid.ciderpay.model.enums.BizKind;
import kr.co.udid.ciderpay.model.enums.SellerType;
import kr.co.udid.ciderpay.model.enums.UserType;
import kr.co.udid.ciderpay.model.exception.ExistDataException;
import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.exception.NotSetRequiredDataException;
import kr.co.udid.ciderpay.model.notYet.UserTypePersonal;
import kr.co.udid.ciderpay.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberSvTest
{
    @Autowired
    MemberRepository memberRepository;

    @Autowired MemberSv memberSv;

    @Test
    public Member makeMember() {
        Member m = new Member ();

        m.setParentMemberID ("parentMemberID").setSeller_type (SellerType.SELLER).setUserid ("userid").setUserpwd ("userpwd")
                .setSellername ("sellername").setPhone ("phone").setEmail ("email")
                .setBizkind (BizKind.BLOG).setUsertype (UserType.PERSONAL).setPersonalType (new UserTypePersonal ("username"));


        return memberRepository.save(m);
    }


    @Test
    void existMemberID () throws NoDataException
    {
        assertFalse (memberSv.existMemberID ("test", "Test"));

    }

    @Test
    void regist () throws NoDataException, ExistDataException, NotSetRequiredDataException
    {
        MemberRegisterData data = new MemberRegisterData ();
        data.setResellerid ("test");
        data.setSeller_type (SellerType.SELLER);
        data.setUserid ("test");
        data.setUserpwd ("password");
        data.setSellername ("test");
        data.setPhone ("test");
        data.setEmail ("Test");
        data.setBizkind ("Blog사업자");
        data.setUsertype ("1");
        data.setUsername ("name");

        assertEquals (memberSv.regist (data), "test");
    }
}