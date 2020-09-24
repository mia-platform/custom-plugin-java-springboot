package eu.miaplatform.customplugin.springboot;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import static eu.miaplatform.customplugin.springboot.CPConstants.CP_REQUEST;

@RestController
public class CPStatusController extends CPController{
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

    @GetMapping("/-/check-up")
    @ApiOperation(value = "Check up")
    public ResponseEntity checkUp(@ApiIgnore @ModelAttribute(CP_REQUEST) CPRequest cpRequest) {
        return checkUpHandler(cpRequest);
    }

    public ResponseEntity healthinessHandler(CPRequest cpRequest) {
        return CPStatus.statusOk(new CPStatusBody());
    }

    public ResponseEntity readinessHandler(CPRequest cpRequest) {
        return CPStatus.statusOk(new CPStatusBody());
    }

    public ResponseEntity checkUpHandler(CPRequest cpRequest) {
        return CPStatus.statusOk(new CPStatusBody());
    }
}
