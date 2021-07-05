package com.example.crudapp;

public class User {
    String id;
    String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    String salary;
    String doj;

    public User(String id, String name, String phone, String age, String salary, String doj) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.salary = salary;
        this.doj = doj;
    }

    public User(String name, String phone, String age, String salary, String doj) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.salary = salary;
        this.doj = doj;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String name, phone;
}
