package kr.co.udid.ciderpay.service.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumConverter
{
    public static <E extends Enum<E>> Enum convert (Class<E> e, String data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Enum[] enums = e.getEnumConstants ();

        Enum result = null;

        for (Enum en : enums) {
            if (data.equals (en.getClass ().getMethod ("getName").invoke (en))) {
                result = en;
                break;
            }
        }

        return result;
    }
}
