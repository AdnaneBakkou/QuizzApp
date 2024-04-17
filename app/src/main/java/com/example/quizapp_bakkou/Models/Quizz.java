package com.example.quizapp_bakkou.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Quizz {
    // contenue de la classe quizz
    private String id;
    private String question;
    private String repCorrect;

    // list des reponses
    private List<String> reponses = new ArrayList<>();

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;
    public Quizz(){
        this.id = String.valueOf(UUID.randomUUID());
    }

    public Quizz(String question, String repCorrect, List<String> reponses) {
        this.id = String.valueOf(UUID.randomUUID());
        this.question = question;
        this.repCorrect = repCorrect;
        this.reponses = reponses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRepCorrect() {
        return repCorrect;
    }

    public void setRepCorrect(String repCorrect) {
        this.repCorrect = repCorrect;
    }

    public List<String> getReponses() {
        return reponses;
    }

    public void setReponses(List<String> reponses) {
        this.reponses = reponses;
    }
}