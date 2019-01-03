package eu.miaplatform.customplugin.springboot.lib;

public class CustomPluginOptions {
    private String userIdHeaderKey;
    private String groupsHeaderKey;
    private String clientTypeHeaderKey;
    private String backofficeHeaderKey;
    private String microserviceGatewayServiceName;

    public CustomPluginOptions() {
        this.userIdHeaderKey = "ID";
        this.groupsHeaderKey = "GROUP";
        this.clientTypeHeaderKey = "CLIENT_TYPE";
        this.backofficeHeaderKey = "BACKOFFICE";
        this.microserviceGatewayServiceName = "MICROSERVICE";
    }
    public CustomPluginOptions(String userIdHeaderKey, String groupsHeaderKey, String clientTypeHeaderKey, String backofficeHeaderKey, String microserviceGatewayServiceName) {
        this.userIdHeaderKey = userIdHeaderKey;
        this.groupsHeaderKey = groupsHeaderKey;
        this.clientTypeHeaderKey = clientTypeHeaderKey;
        this.backofficeHeaderKey = backofficeHeaderKey;
        this.microserviceGatewayServiceName = microserviceGatewayServiceName;
    }

    public String getUserIdHeaderKey() {
        return userIdHeaderKey;
    }

    public String getGroupsHeaderKey() {
        return groupsHeaderKey;
    }

    public String getClientTypeHeaderKey() {
        return clientTypeHeaderKey;
    }

    public String getBackofficeHeaderKey() {
        return backofficeHeaderKey;
    }

    public String getMicroserviceGatewayServiceName() {
        return microserviceGatewayServiceName;
    }
}
