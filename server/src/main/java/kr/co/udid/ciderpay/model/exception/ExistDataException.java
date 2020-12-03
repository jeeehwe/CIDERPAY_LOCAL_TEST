package kr.co.udid.ciderpay.model.exception;

public class ExistDataException extends Exception
{
    private static final long	serialVersionUID	= 1689842079703549586L;

    public ExistDataException ()
    {
    }

    public ExistDataException (String message)
    {
        super (message);
    }
}
