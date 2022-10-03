package ru.yandex.praktikum.model;

public class UserData {

    private String email;
    private String password;
    private String name;
    private Boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
    private String message;

    public UserData(String email, String password, String name, Boolean success, User user, String accessToken, String refreshToken, String message) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
    }

    public UserData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMessage() {
        return message;
    }
}
