package kr.co.udid.ciderpay.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ProcessStatusException extends Exception
{
    public ProcessStatusException (String message)
    {
        super (message);
    }
}
