package com.example.covid_19;

public class country {
    public String Name,Image;
    public country() {
    }

    public country(String name, String image) {
        Name = name;
        Image = image;

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
