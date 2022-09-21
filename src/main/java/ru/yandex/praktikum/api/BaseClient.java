package ru.yandex.praktikum.api;

import io.restassured.response.Response;
import ru.yandex.praktikum.model.Client;

import static io.restassured.RestAssured.given;

public class BaseClient {
    protected static final String BaseURL = "https://stellarburgers.nomoreparties.site";
    protected static final String registrationURL = BaseURL + "/register";
    protected static final String deleteClientAPI = BaseURL + "/api/auth/user";
    protected static final String createClientAPI = BaseURL +"/api/auth/register";
    protected static final String loginAPI = BaseURL +"/login";

    public static Response deleteClient(Client client) {
        return given()
                .header("Content-type", "application/json")
                .body(client)
                .delete(deleteClientAPI);
    }

    public static Response createClient(Client client) {
        return given()
                .header("Content-type", "application/json")
                .body(client)
                .post(createClientAPI);
    }
}
