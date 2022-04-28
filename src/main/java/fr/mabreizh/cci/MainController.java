package fr.mabreizh.cci;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

@ExecuteOn(TaskExecutors.IO)
@Controller
public class MainController {
    
    @Inject private HelloService helloService;
    
    @Get
    public String fecthHello() {
        return helloService.hello();
    }
}
