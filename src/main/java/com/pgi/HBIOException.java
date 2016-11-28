package com.pgi;
import java.io.IOException;

/**
 * Created by sandeep on 28/11/16.
 */
public class HBIOException extends IOException {
    public int status;
    public String response;

    HBIOException(int status, String response)
    {
        this.status = status;
        this.response = response;
    }
}

