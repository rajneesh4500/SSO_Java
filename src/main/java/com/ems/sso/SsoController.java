package com.ems.sso;

import com.ems.sso.constants.SSOConstants;
import com.ems.sso.model.InputUser;
import com.ems.sso.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(SSOConstants.SSO_MAPPING)
public class SsoController {

    @Autowired
    private SsoService ssoService;

    @RequestMapping(SSOConstants.VALIDATE_USER)
    public ResponseEntity<Integer> validateUser(@RequestParam(name = "userId") String userId){
        return ssoService.validateUser(userId);
    }

    @RequestMapping(value = SSOConstants.VALIDATE_PASS_PATH,method = RequestMethod.POST)
    public ResponseEntity<String> validatePass(@RequestParam(name = "userId") Integer userId, @RequestBody InputUser user){
        return ssoService.valditePass(userId,user.getPassword());
    }

    @RequestMapping(value = SSOConstants.VALIDATE_USER_SESSION,method = RequestMethod.POST)
    public ResponseEntity<Boolean> validateSession( @RequestBody InputUser user){
        return ssoService.validateSession(user.getUserid(),user.getToken());
    }
}
