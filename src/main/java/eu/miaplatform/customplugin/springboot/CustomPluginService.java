package eu.miaplatform.customplugin.springboot;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class CustomPluginService {

    public <T extends Serializable> T addHandler(CustomPluginRequest request, Function<CustomPluginRequest, T> handler) {
        return handler.apply(request);
    }

    public void addHandler(CustomPluginRequest request, Consumer<CustomPluginRequest> handler) {
        handler.accept(request);
    }
}
