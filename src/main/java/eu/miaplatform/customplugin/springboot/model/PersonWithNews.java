package eu.miaplatform.customplugin.springboot.model;

import java.io.Serializable;

public class PersonWithNews implements Serializable {

    private String personName;
    private String newsTitle;

    public PersonWithNews(String personName, String newsTitle) {
        this.personName = personName;
        this.newsTitle = newsTitle;
    }

    public String getPersonName() {
        return personName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }
}
