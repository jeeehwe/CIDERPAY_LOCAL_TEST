package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.common.ErrorCode;
import kr.co.udid.ciderpay.model.member.data.MemberRegisterData;
import kr.co.udid.ciderpay.model.common.exception.ExistDataException;
import kr.co.udid.ciderpay.model.common.exception.NoDataException;
import kr.co.udid.ciderpay.model.common.exception.NotSetRequiredDataException;
import kr.co.udid.ciderpay.model.common.result.CommonResult;
import kr.co.udid.ciderpay.model.member.result.ExistResult;
import kr.co.udid.ciderpay.model.common.result.FailMessage;
import kr.co.udid.ciderpay.model.member.result.MemberResult;
import kr.co.udid.ciderpay.service.MemberSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/oapi/member")
public class MemberApiCt
{
    final private MemberSv memberSv;

    @GetMapping("/exist")
    public ExistResult existMemberID (@RequestParam String memberID, @RequestParam String parentMemberID)
    {
        ExistResult result = new ExistResult ();

        try
        {
            boolean exist = memberSv.existMemberID (memberID, parentMemberID);

            result.setExist (exist? "Y" : "N");
        }
        catch (NoDataException e)
        {
            result.setSuccess (false);
            result.setExist ("E");
            result.setErrorMessage (e.getMessage ());
        }

        return result;
    }

    @PostMapping("/regist/seller/v2")
    public Object regist (MemberRegisterData data) throws IllegalArgumentException
    {
        CommonResult result = new CommonResult ();

        try
        {
            String memberID = memberSv.regist (data);

            result.setResult (new MemberResult (memberID));
        }
        catch (NotSetRequiredDataException | NoDataException e)
        {
            result.setResult (new FailMessage ("", e.getMessage ()));
        }
        catch (ExistDataException e)
        {
            result.setResult (new FailMessage (ErrorCode.ERRROR_REGIST_MEMBER.getErrorCode (), ErrorCode.ERRROR_REGIST_MEMBER.name ()));
        }

        return result.getResult ();
    }
}
