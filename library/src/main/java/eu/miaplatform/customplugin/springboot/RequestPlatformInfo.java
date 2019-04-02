package eu.miaplatform.customplugin.springboot;

import java.util.List;

public interface RequestPlatformInfo {

    public String getUserId();

    public List<String> getGroups();

    public String getClientType();

    public boolean isFromBackOffice();
}
