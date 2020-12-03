package kr.co.udid.ciderpay.model.common.exception;

public class NoDataException extends Exception
{
    private static final long	serialVersionUID	= -7044189756706237640L;

    public NoDataException ()
    {
    }

    public NoDataException (String message)
    {
        super (message);
    }
}
