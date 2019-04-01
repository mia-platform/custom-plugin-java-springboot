package eu.miaplatform.customplugin.springboot.lib;

public class Options {
    private String userIdHeaderKey;
    private String groupsHeaderKey;
    private String clientTypeHeaderKey;
    private String backofficeHeaderKey;
    private String microserviceGatewayServiceName;

    public Options() {
        this.userIdHeaderKey = "USERID_HEADER_KEY";
        this.groupsHeaderKey = "GROUPS_HEADER_KEY";
        this.clientTypeHeaderKey = "CLIENTTYPE_HEADER_KEY";
        this.backofficeHeaderKey = "BACKOFFICE_HEADER_KEY";
        this.microserviceGatewayServiceName = "MICROSERVICE_HEADER_KEY";
    }
    public Options(String userIdHeaderKey, String groupsHeaderKey, String clientTypeHeaderKey, String backofficeHeaderKey, String microserviceGatewayServiceName) {
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
