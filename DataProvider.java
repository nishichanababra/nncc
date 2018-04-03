package com.company.ecommerce.recyclerviewdemo;

/**
 * Created by Brijesh on 03-04-18.
 */

public class DataProvider {

    private int image_id;
    private String name,description;

    public DataProvider(int image_id, String name, String description) {
        this.setImage_id(image_id);
        this.setName(name);
        this.setDescription(description);
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
