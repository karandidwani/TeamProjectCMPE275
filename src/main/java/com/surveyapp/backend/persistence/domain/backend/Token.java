package com.surveyapp.backend.persistence.domain.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

@Entity
public class Token {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String tokenId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surveyId")
    private SurveyEntity survey;


    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_tokens",
            joinColumns = { @JoinColumn(name = "tokenId") },
            inverseJoinColumns = { @JoinColumn(name = "pId") })
             private Set<Participant> participants = new HashSet<>();


//    @Transient
//    @OneToMany(mappedBy = "token", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Participant> participants;

    public Token(SurveyEntity survey) {
        this.survey = survey;
       // this.participants = participants;
    }

    public Token() {
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }
}
