package kr.co.udid.ciderpay.service.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class Util
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

    public static void myCopyProperties (Object src, Object target)
    {
        BeanUtils.copyProperties (src, target, getNullPropertyNames (src));
    }

    public static String[] getNullPropertyNames (Object source)
    {
        final BeanWrapper src = new BeanWrapperImpl (source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors ();

        Set<String> emptyNames = new HashSet<String> ();
        for (java.beans.PropertyDescriptor pd : pds)
        {
            Object srcValue = src.getPropertyValue (pd.getName ());
            if (srcValue == null) emptyNames.add (pd.getName ());
        }
        String[] result = new String[emptyNames.size ()];
        return emptyNames.toArray (result);
    }
}
