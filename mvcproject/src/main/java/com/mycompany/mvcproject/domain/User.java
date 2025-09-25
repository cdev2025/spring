package com.mycompany.mvcproject.domain;

public class User {
    // 상수 필드로 로그인 정보 정의 (DB 정보 대체)
    public static final String DEFAULT_EMAIL = "test@test.com";
    public static final String DEFAULT_PASSWORD = "1234!";
    public static final String DEFAULT_NAME = "홍길동";

    private int id; // DB의 primary key 역할
    private String name;
    private String email;
    private String password;
    private Integer age;

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age ;
    }
}
