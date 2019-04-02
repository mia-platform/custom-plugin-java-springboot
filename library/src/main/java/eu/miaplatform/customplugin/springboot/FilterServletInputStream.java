package eu.miaplatform.customplugin.springboot;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilterServletInputStream extends ServletInputStream {

    private InputStream input;

    public FilterServletInputStream (InputStream input) {
        this.input = input;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    public int read() throws IOException
    {
        return input.read();
    }
}
