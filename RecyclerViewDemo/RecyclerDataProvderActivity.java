package com.example.brijesh.recyclerviewdemo;

/**
 * Created by Brijesh on 08-01-18
 * description: RecyclerProviderClass.
 */

public class RecyclerDataProvderActivity {

    private String imgrecycler;
    private String name;
    private String description;


    public RecyclerDataProvderActivity(String imgrecycler, String name, String description) {
        this.imgrecycler = imgrecycler;
        this.name = name;
        this.description = description;
    }

    public String getImgrecycler() {
        return imgrecycler;
    }

    public void setImgrecycler(String imgrecycler) {
        this.imgrecycler = imgrecycler;
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
//end of RecyclerDataProvderActivity
