package fr.mabreizh.cci;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest()
class ClientCredentialIssueTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testItWorks() throws InterruptedException {
        String response = client.toBlocking().retrieve(HttpRequest.GET("/"));
        Assertions.assertEquals("Hi my-token !", response);
        Thread.sleep(2000);
        // all calls will then fails
        for(int i = 0; i < 10; i++) {
            try {
                response = client.toBlocking().retrieve(HttpRequest.GET("/"));
                Assertions.fail("should not be ok");
            } catch (HttpClientResponseException ignored) {}
        }
    }

}
