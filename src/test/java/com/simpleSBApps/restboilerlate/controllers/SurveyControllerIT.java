package com.simpleSBApps.restboilerlate.controllers;

import com.simpleSBApps.restboilerlate.Main;
import com.simpleSBApps.restboilerlate.models.Question;
import javafx.application.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @BeforeEach
    public void before() {
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    private String getUrlForQuestion(String id) {
        return getBaseUrl() + "/surveys/Survey1/questions/" + id;
    }

    private String getUrlForSurveyQuestions() {
        return getBaseUrl() + "/surveys/Survey1/questions";
    }

    @Test
    public void testRetrieveQuestion() {
        System.out.println("PORTPORT" + port);

        String url = getUrlForQuestion("Question1");
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

    @Test
    public void testAddQuestion() {

        Question question =  new Question("x",
                "Most populous island in the world", "Java", Arrays.asList(
                "Java", "Great Britain", "Honshu", "Madagascar"));

        String url = getUrlForSurveyQuestions();
        HttpEntity httpEntity = new HttpEntity<Question>(question, httpHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println(actual);
        assert(actual.contains("/surveys/Survey1/questions"));

    }
}
