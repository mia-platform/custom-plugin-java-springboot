package eu.miaplatform.customplugin.springboot;

import java.io.Serializable;

public class CPStatusBody implements Serializable {

    public static final String OK = "OK";
    public static final String KO = "KO";

    private String name = "Custom Plugin Spring Boot REST API";
    private String version = "0.0.1-SNAPSHOT";
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
