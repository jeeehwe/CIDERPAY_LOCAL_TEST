package kr.co.udid.ciderpay.controller;

import kr.co.udid.ciderpay.model.data.MemberRegisterData;
import kr.co.udid.ciderpay.model.exception.ExistDataException;
import kr.co.udid.ciderpay.model.exception.NoDataException;
import kr.co.udid.ciderpay.model.exception.NotSetRequiredDataException;
import kr.co.udid.ciderpay.model.result.CommonResult;
import kr.co.udid.ciderpay.model.result.ExistResult;
import kr.co.udid.ciderpay.model.result.FailMessage;
import kr.co.udid.ciderpay.model.result.member.MemberResult;
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
        catch (NotSetRequiredDataException | ExistDataException e)
        {
            result.setResult (new FailMessage ("", e.getMessage ()));
        }
        catch (NoDataException e)
        {
            result.setResult (new FailMessage ("1090", e.getMessage ()));
        }

        return result.getResult ();
    }
}
