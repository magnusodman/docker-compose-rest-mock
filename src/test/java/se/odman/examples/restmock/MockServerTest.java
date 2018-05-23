package se.odman.examples.restmock;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Header;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class MockServerTest {

  @Test
  public void testSomething() {

    //Set up mocked responses
    //Assumes that docker compose is started with the mockserver service. See docker-compose.yml in this project.
    new MockServerClient("127.0.0.1", 1080)
        .when(
            request()
                .withMethod(HttpMethod.GET.name())
                .withPath("/endpoint")
        )
        .respond(
            response()
                .withStatusCode(200)
                .withHeaders(new Header(HttpHeaders.CONTENT_TYPE, "application/xml"))
                .withBody("<?xml version=\"1.0\"?><response>something</response>")
                .withDelay(TimeUnit.SECONDS,1)
        );

    //Do the real testing
    //The mocking above will respond to: curl http://localhost:1080/endpoint

    //......

  }




}
