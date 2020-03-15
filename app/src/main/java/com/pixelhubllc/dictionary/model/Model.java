package com.pixelhubllc.dictionary.model;

public class Model {
    private int id;
    private String en_words;
    private String en_defination;
    private String example;
    private String synonyms;
    private String antonyms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEn_words() {
        return en_words;
    }

    public void setEn_words(String en_words) {
        this.en_words = en_words;
    }

    public String getEn_defination() {
        return en_defination;
    }

    public void setEn_defination(String en_defination) {
        this.en_defination = en_defination;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

    public Model(int id, String en_words, String en_defination, String example, String synonyms, String antonyms) {
        this.id = id;
        this.en_words = en_words;
        this.en_defination = en_defination;
        this.example = example;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }
    public Model(int id, String en_words) {
        this.id = id;
        this.en_words = en_words;
    }


}
