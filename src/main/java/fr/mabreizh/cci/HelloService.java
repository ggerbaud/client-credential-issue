package fr.mabreizh.cci;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/hello")
public interface HelloService {
    @Get
    String hello();
}
