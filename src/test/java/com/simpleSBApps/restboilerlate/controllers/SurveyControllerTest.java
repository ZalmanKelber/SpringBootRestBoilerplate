package com.simpleSBApps.restboilerlate.controllers;

import com.simpleSBApps.restboilerlate.models.Question;
import com.simpleSBApps.restboilerlate.services.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    public void retrieveQuestionDetails() throws Exception {

        Question mockQuestion = new Question("QuestionX",
                "Most populous island in the world", "Java", Arrays.asList(
                "Java", "Great Britain", "Honshu", "Madagascar"));

        Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(mockQuestion);

        String url = "/surveys/Survey1/questions/QuestionX";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String actual = result.getResponse().getContentAsString();
        String expected = "{id:QuestionX,correctAnswer:Java}";
        JSONAssert.assertEquals(expected, actual, false);

    }

    @Test
    public void createSurveyQuestion() throws Exception {

        Question mockQuestion = new Question("1", "Smallest Number", "1",
                Arrays.asList("1", "2", "3", "4"));

        String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";

        Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)))
                .thenReturn(mockQuestion);

        String url = "/surveys/Survey1/questions";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .content(questionJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
    }
}
