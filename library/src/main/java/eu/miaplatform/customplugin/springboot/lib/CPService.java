package eu.miaplatform.customplugin.springboot.lib;

import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class CPService {

    public <T> T addHandler(CPRequest request, Function<CPRequest, T> handler) {
        return handler.apply(request);
    }

    public void addHandler(CPRequest request, Consumer<CPRequest> handler) {
        handler.accept(request);
    }
}
