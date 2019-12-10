package com.example.learning_app.Models;

public class CountryModel {
    int id;
    String name;
    int phonecode;
    public CountryModel(String name,int id){
        this.name=name;
      this.id=id;
//        this.phonecode=phonecode;

    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(int phonecode) {
        this.phonecode = phonecode;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
