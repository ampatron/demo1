package com.uxr.demo.one.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Date;
import java.util.List;

/**
 * Created by abigail on 6/1/2016.
 */
@JsonObject
public class Task {
    @JsonField
    private Company company;
    @JsonField
    private List<Image> images;
    @JsonField
    private String title;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

