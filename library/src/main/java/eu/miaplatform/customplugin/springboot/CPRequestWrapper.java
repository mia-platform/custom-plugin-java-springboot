package eu.miaplatform.customplugin.springboot;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CPRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;
    private Map<String, String[]> modifiableQueryParameters = new TreeMap<>();
    private Map<String, String> headerMap = new HashMap<String, String>();


    public CPRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        InputStream is = super.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while (reads != -1) {
            baos.write(reads);
            reads = is.read();
        }
        body = baos.toByteArray();
        modifiableQueryParameters.putAll(super.getParameterMap());
    }

    @Override
    public ServletInputStream getInputStream() {
        return new FilterServletInputStream(new ByteArrayInputStream(body));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        String enc = getCharacterEncoding();
        if (enc == null) enc = "UTF-8";
        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }

    public Map<String, String[]> getQueryParameters() {
        return modifiableQueryParameters;
    }

    public void setQueryParameters(Map<String, String[]> queryParameters) {
        this.modifiableQueryParameters = queryParameters;
    }

    @Override
    public String getParameter(final String name) {
        String[] strings = getParameterMap().get(name);
        if (strings != null) {
            return strings[0];
        }
        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        //Return an unmodifiable collection because we need to uphold the interface contract.
        return Collections.unmodifiableMap(modifiableQueryParameters);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(final String name) {
        return getParameterMap().get(name);
    }


    public String getPath() {
        return this.getServletPath();
    }

    public String getBody() throws IOException {
        return this.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public void setBody(String body) {
        this.body = body.getBytes();
    }
}
