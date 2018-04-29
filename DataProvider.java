package com.company.adapter;

/**
 * Created by nishi on 01-03-18.
 */

public class DataProvider {
    private int id_count = 0;
    private int imgrecycler;

    public int getId_count() {
        return id_count;
    }

    public void setId_count(int id_count) {
        this.id_count = id_count;
    }

    private String name;
    private String price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public int getImgrecycler() {
        return imgrecycler;
    }

    public void setImgrecycler(int imgrecycler) {
        this.imgrecycler = imgrecycler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}//end of RecyclerDataProvderActivity
