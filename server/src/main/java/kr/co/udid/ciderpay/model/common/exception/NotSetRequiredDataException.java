package kr.co.udid.ciderpay.model.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class NotSetRequiredDataException extends Exception
{
    public NotSetRequiredDataException (String message)
    {
        super (message);
    }
}
