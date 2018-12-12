package eu.miaplatform.customplugin.springboot;

public class Route {

    public enum Method {
        GET,
        POST
    }

    private final String path;
    private final Method method;
    private final CustomPluginController controller;


    public Route(String path, Method method, CustomPluginController controller) {
        this.path = path;
        this.method = method;
        this.controller = controller;
    }

    public String getPath() {
        return path;
    }

    public Method getMethod() {
        return method;
    }

    public CustomPluginController getController() {
        return controller;
    }
}
