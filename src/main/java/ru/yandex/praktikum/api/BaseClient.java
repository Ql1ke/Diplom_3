package ru.yandex.praktikum.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.yandex.praktikum.model.UserData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BaseClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTRATION_URL = BASE_URL + "/register";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String DELETE_CLIENT_API_URL = BASE_URL + "/api/auth/user";
    public static final String CREATE_CLIENT_API_URL = BASE_URL + "/api/auth/register";
    public static final String LOGIN_API_API_URL = BASE_URL + "/api/auth/login";


    public static final Map<String, String> tokens = new HashMap<>();


    public static void deleteClient(UserData userData) {
        String token = tokens.get(userData.getEmail());

        if (token != null) {
            given()
                    .header("Content-type", "application/json")
                    .header("authorization", tokens.get(userData.getEmail()))
                    .delete(DELETE_CLIENT_API_URL);
        }
    }


    public static Response createClient(UserData userData) {
        Response resp = given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(CREATE_CLIENT_API_URL);
        tokens.put(userData.getEmail(), resp.getHeader("authorization"));
        return resp;
    }

    public static void loginClient(UserData userData) {
        UserData resp = given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(LOGIN_API_API_URL).then().extract().as(UserData.class);
        tokens.put(userData.getEmail(), resp.getAccessToken());
    }
}
