# custom-plugin-java-springboot
Library that allows you to create Spring Boot services to deploy on
[Mia-Platform](https://www.mia-platform.eu) together with
[custom-plugin-java](https://github.com/mia-platform/custom-plugin-java).

## Usage
### Route controller
In order to handle HTTP requests, you have to create a class that extends `CPController`.
``` java
    public class HelloWorldController extends CPController {
        // Endpoints defined here
    }
```

### Defining an endpoint
To define an endpoint, you have to add a method to your controller. The method receives a `CPRequest` as input. For example:

``` java
   @GetMapping("/hello")
   @ResponseBody
   public String helloWorld(@ModelAttribute(CP_REQUEST) CPRequest cpRequest) {
       return "Hello World!";
   }
```

### PRE decorators
To define a PRE decorator, you have to add a controller's method (HTTP POST handler) which receives a `PreDecoratorRequest` parameter (tagged with `@RequestBody` for deserialization)
and returns a `ResponseEntity`. The `ResponseEntity` instance can be obtained through the method `DecoratorUtils.getResponseEntityFromDecoratorResponse(DecoratorResponse decoratorResponse)`.
For example:

``` java
    @PostMapping("/addToken")
    @ResponseBody
    public ResponseEntity addHeaderToken(@RequestBody PreDecoratorRequest request) {
        Map<String, String> originalHeaders = request.getOriginalRequestHeaders();
        Map<String, String> newHeaders = new HashMap<>(originalHeaders);
        newHeaders.put("x-token", "my-token");
        PreDecoratorRequest updatedRequest = request.changeOriginalRequest()
                .setHeaders(newHeaders)
                .build();

        DecoratorResponse response = DecoratorResponseFactory.makePreDecoratorResponse(updatedRequest);
        return DecoratorUtils.getResponseEntityFromDecoratorResponse(response);
    }
```

### POST decorators
To define a POST decorator, you have to add a controller's method (HTTP POST handler) which receives a `PostDecoratorRequest` parameter (tagged with `@RequestBody` for deserialization)
and returns a `ResponseEntity`. The `ResponseEntity` instance can be obtained through the method `DecoratorUtils.getResponseEntityFromDecoratorResponse(DecoratorResponse decoratorResponse)`.
For example:

``` java
    @PostMapping("/checkStatusCode")
    @ResponseBody
    public ResponseEntity checkStatusCode(@RequestBody PostDecoratorRequest request) {
        if (request.getOriginalResponseStatusCode == 404) {
            DecoratorResponse decoratorResponse = DecoratorResponseFactory.abortChain(401);
        } else {
            DecoratorResponse decoratorResponse = DecoratorResponseFactory.makePostDecoratorResponse(request.leaveOriginalResponseUnmodified()};
        }
        return DecoratorUtils.getResponseEntityFromDecoratorResponse(decoratorResponse);
    }
```
