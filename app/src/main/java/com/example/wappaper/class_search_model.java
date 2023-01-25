package com.example.wappaper;

import java.util.ArrayList;

public class class_search_model {

    private ArrayList<class_img_model> photos;

    public ArrayList<class_img_model> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<class_img_model> photos) {
        this.photos = photos;
    }

    public class_search_model(ArrayList<class_img_model> photos) {
        this.photos = photos;
    }
}
