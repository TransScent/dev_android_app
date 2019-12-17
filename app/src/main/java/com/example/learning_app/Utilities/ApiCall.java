package com.example.learning_app.Utilities;

public class ApiCall {
    public String getCountryList(){ return "http://192.168.42.54:8020/api/learning/getCountriesList";
    }
    public String getStateList(){
        return "http://192.168.42.54:8020/api/learning/getStateList";
    }
}
