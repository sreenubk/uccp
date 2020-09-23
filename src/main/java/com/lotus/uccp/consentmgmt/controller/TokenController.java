package  com.lotus.uccp.consentmgmt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lotus.uccp.authentication.model.AbacUser;
import com.lotus.uccp.auth.security.service.AbacTokenGenerator;





@RestController
@RequestMapping("/token")
public class TokenController {
	
	private AbacTokenGenerator abacTokenGenerator;

    public TokenController(AbacTokenGenerator abacTokenGenerator) {
        this.abacTokenGenerator = abacTokenGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final AbacUser abacUser) {

        return abacTokenGenerator.generate(abacUser);

    }

}
