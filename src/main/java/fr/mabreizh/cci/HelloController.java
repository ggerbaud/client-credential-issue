package fr.mabreizh.cci;

import io.micronaut.core.util.StringUtils;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@ExecuteOn(TaskExecutors.IO)
@Controller("hello")
public class HelloController {

    private static final String BEARER_PREFIX = "Bearer ";

    @Get
    public String hello(@Header String authorization) {
        String token = StringUtils.hasText(authorization) && authorization.startsWith(BEARER_PREFIX) ? authorization.substring(BEARER_PREFIX.length()) : authorization;
        return "Hi " + token + " !";
    }

}
