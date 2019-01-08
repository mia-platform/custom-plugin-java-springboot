package eu.miaplatform.customplugin.springboot.lib;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

class Utils {

    static String getUserId(Options options, Map<String, String> headers) {
        return headers.get(options.getUserIdHeaderKey());
    }

    static List<String> getGroups(Options options, Map<String, String> headers) {
        String header = headers.get(options.getGroupsHeaderKey());
        String[] groupHeaders = header != null ? header.split(",") : new String[]{};
        return Arrays.stream(groupHeaders).filter(s -> s.length() > 0).collect(Collectors.toList());
    }

    static String getClientType(Options options, Map<String, String> headers) {
        return headers.get(options.getClientTypeHeaderKey());
    }

    static boolean isFromBackOffice(Options options, Map<String, String> headers) {
        String header = headers.get(options.getBackofficeHeaderKey());
        return header != null ? header.length() > 0 : false;
    }

    static Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
