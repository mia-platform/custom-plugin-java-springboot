package eu.miaplatform.customplugin.springboot.lib;

import eu.miaplatform.customplugin.springboot.SwaggerConfig;

import java.io.Serializable;

public class CPStatusBody implements Serializable {

    public static final String OK = "OK";
    public static final String KO = "KO";

    private String name = new SwaggerConfig().getTitle();
    private String version = new SwaggerConfig().getVersion();
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
