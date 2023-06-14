package com.example.mini_project01;

public class User {
    private  String firstname;
    private  String lastname;
    private  String gender;
    private  String city;



    private String image;
    public User(String firstname, String lastname, String gender, String city, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.city = city;
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }
    public String getImage() {return image;}


    public  String fullname(){
        return String.format("%s %s",firstname, lastname.toUpperCase());
    }

    @Override
    public String toString(){
        return String.format("Hi i am %s I am %s \nI live in %s",fullname(), gender.equals("male") ? "♂" : "♀", city);
    }

}
