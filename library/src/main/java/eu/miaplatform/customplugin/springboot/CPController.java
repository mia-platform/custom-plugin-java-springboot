package eu.miaplatform.customplugin.springboot;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController
public abstract class CPController {

    protected final String CP_REQUEST = "CP_REQUEST";
    protected final Logger logger = LoggerFactory.getLogger(CPController.class);

    @Autowired
    protected CPService customPluginService;

    @ModelAttribute(CP_REQUEST)
    public CPRequest populate(HttpServletRequest request) {
        return new CPRequest(request, new Options());
    }

    @GetMapping("/-/healthz")
    @ApiOperation(value = "Healthz")
    @ResponseBody
    public ResponseEntity healthz(@ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest) {
        return healthinessHandler(cpRequest);
    }


    @GetMapping("/-/ready")
    @ApiOperation(value = "Ready")
    public ResponseEntity ready(@ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest) {
        return readinessHandler(cpRequest);
    }


    public ResponseEntity healthinessHandler (CPRequest cpRequest) {
        return CPStatus.statusOk(new CPStatusBody());
    }

    public ResponseEntity readinessHandler (CPRequest cpRequest) {
        return CPStatus.statusKo(new CPStatusBody());
    }
}
