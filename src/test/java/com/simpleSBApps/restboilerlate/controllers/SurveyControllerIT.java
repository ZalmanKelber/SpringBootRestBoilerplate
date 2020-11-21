package com.simpleSBApps.restboilerlate.controllers;

import com.simpleSBApps.restboilerlate.Main;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = Main.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

    @LocalServerPort
    private int port;

    @Test
    public void testRetrieveQuestion() {
        System.out.println("PORTPORT" + port);

        String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        String expected = "{id:Question1,correctAnswer:Russia}";
        String actual = response.getBody();

        try {
            JSONAssert.assertEquals(expected, actual, false);
        } catch (Exception ex) {
            System.out.println("JSONAssert through exception: " + ex);
        }

    }
}
