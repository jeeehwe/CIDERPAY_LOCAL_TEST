package kr.co.udid.ciderpay.controller.view;

import kr.co.udid.ciderpay.model.Member;
import kr.co.udid.ciderpay.service.MemberSv;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/m")
public class MemberViewCt
{
    private final MemberSv memberSv;

    @GetMapping("/{userid}")
    public Member getMember (@PathVariable String userid)
    {
        return memberSv.getMember (userid);
    }
}
