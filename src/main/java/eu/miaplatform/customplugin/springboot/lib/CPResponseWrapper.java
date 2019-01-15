package eu.miaplatform.customplugin.springboot.lib;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class CPResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream output;
    private FilterServletOutputStream filterOutput;

    public CPResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new FilterServletOutputStream(output);
        }
        return filterOutput;
    }

    public String getBody() {
        return new String(output.toByteArray());
    }


    public void setBody(String body) throws IOException {
        this.getResponse().getOutputStream().write(body.getBytes());
    }
}
