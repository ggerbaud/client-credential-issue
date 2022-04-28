package fr.mabreizh.cci;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.oauth2.endpoint.token.response.TokenResponse;

import java.util.concurrent.atomic.AtomicBoolean;

@ExecuteOn(TaskExecutors.IO)
@Controller("token")
public class TokenController {
    
    private final AtomicBoolean shouldThrow = new AtomicBoolean(false);
    
    @Post(consumes = "*/*")
    public TokenResponse token() {
        if(shouldThrow.compareAndSet(true, false)) {
            throw new RuntimeException("pit");
        }
        shouldThrow.set(true);
        var resp =  new TokenResponse("my-token", "custom");
        resp.setExpiresIn(31);
        return resp;
    }
}
