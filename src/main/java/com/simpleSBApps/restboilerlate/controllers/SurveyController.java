package com.simpleSBApps.restboilerlate.controllers;

import com.simpleSBApps.restboilerlate.models.Question;
import com.simpleSBApps.restboilerlate.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> getQuestionsForSurvey(@PathVariable String surveyId) {
        return surveyService.retrieveQuestions(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question getQuestionsDetails(@PathVariable String surveyId, @PathVariable String questionId) {
        return surveyService.retrieveQuestion(surveyId, questionId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Void> postQuestion(@PathVariable String surveyId, @RequestBody Question newQuestion) {
        Question addedQuestion = surveyService.addQuestion(surveyId, newQuestion);
        if (addedQuestion == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(addedQuestion.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
