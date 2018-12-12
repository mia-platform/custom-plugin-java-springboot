package eu.miaplatform.customplugin.springboot;


public class MyCustomPlugin extends CustomPlugin {

    public static void main(String[] args) {

        setServerPort("8081");

        addRoute(new Route("/rotta1", Route.Method.GET, new CustomPluginController() {

            @Override
            public String handle() {
                return "rotta1";
            }
        }));

        run(args);
    }

}
