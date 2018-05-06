package com.surveyapp.backend.persistence.domain.backend;

//import org.dom4j.Entity;
//import org.hibernate.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

//import lombok.Data;

@Entity
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qId;

    private String qType;
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surveyId")
   // @JsonBackReference
    private Survey survey;


//    @ManyToMany
//    @JoinTable(name = "question_response",
//            joinColumns = {@JoinColumn(name = "q_Id", referencedColumnName = "qId")},
//            inverseJoinColumns = {@JoinColumn(name = "res_Id", referencedColumnName = "responseId")})


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responses;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options;


    public Question() {
    }

//    public Question(String qType, String description) {
//        this.qType = qType;
//        this.description = description;
//    }

    public Question(String qType, String description, Survey survey, List<QuestionOption> options) {
        this.qType = qType;
        this.description = description;
        this.survey = survey;
        this.options =options;

    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }


    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}