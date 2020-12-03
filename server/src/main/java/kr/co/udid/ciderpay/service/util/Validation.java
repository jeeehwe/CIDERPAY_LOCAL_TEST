package kr.co.udid.ciderpay.service.util;

public class Validation
{
    public static boolean isEmptyStr (String str)
    {
        if (str == null)
            return true;

        if (str.equals (""))
            return true;

        if (str.equals ("null"))
            return true;

        return false;
    }
}
