package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.customplugin.springboot.lib.Options;
import eu.miaplatform.customplugin.springboot.lib.CPRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CPRequestTest {

    private CPRequest cpRequest;
    private Options options = new Options();
    private String id = "1234";
    private String type = "admin";
    private boolean backoffice = true;
    private String groupsString = "group1,group2";
    private List<String> groups = Arrays.asList("group1","group2");

    @Before
    public void setUp() {
        cpRequest = new CPRequest(getRequest(options), options);
    }

    @Test
    public void checkPlatformHeaders() {
        assertEquals(id, cpRequest.getUserId());
        assertEquals(type, cpRequest.getClientType());
        assertEquals(backoffice, cpRequest.isFromBackOffice());
        for (int i = 0; i < groups.size(); ++i) {
            assertEquals(groups.get(i), cpRequest.getGroups().get(i));
        }

    }

    private HttpServletRequest getRequest(Options options) {

        MockHttpServletRequest req = new MockHttpServletRequest();

        req.addHeader(options.getUserIdHeaderKey(), id);
        req.addHeader(options.getClientTypeHeaderKey(), type);
        req.addHeader(options.getBackofficeHeaderKey(), backoffice);
        req.addHeader(options.getGroupsHeaderKey(), groupsString);

        return req;
    }
}
