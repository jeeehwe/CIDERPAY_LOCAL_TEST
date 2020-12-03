package kr.co.udid.ciderpay.model.member.result;

import kr.co.udid.ciderpay.model.common.result.ResultMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberResult extends ResultMessage
{
    private String memberID;

    public MemberResult (String memberID)
    {
        super(true, "");

        this.memberID = memberID;
    }
}
