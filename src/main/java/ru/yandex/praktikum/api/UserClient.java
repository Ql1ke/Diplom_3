package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.model.UserData;

public class UserClient {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String LOGIN_USER = "/api/auth/login";
    public static final String CREATE_USER = "/api/auth/register";
    public static final String USER_DATA = "/api/auth/user";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String REGISTRATION_URL = BASE_URL + "/register";
    private final Filter requestFilter = new RequestLoggingFilter();
    private final Filter responseFilter = new ResponseLoggingFilter();


    @Step("Объявлен метод создания пользователя, отправляющий запрос на сервер")
    public Response createRegistration(UserData registration) {
        return RestAssured.with()
                .filters(requestFilter, responseFilter)
                .baseUri(BASE_URL)
                .body(registration)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .post(CREATE_USER);
    }

    @Step("Login user with credentials {credentials}")
    public Response createAuthorization(UserData registration) {
        return RestAssured.with()
                .filters(requestFilter, responseFilter)
                .baseUri(BASE_URL)
                .body(registration)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .post(LOGIN_USER);
    }

    @Step("Delete user")
    public void deleteUser(String accessToken) {
        RestAssured.with()
                .filters(requestFilter, responseFilter)
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .accept(ContentType.HTML)
                .contentType(ContentType.HTML)
                .delete(USER_DATA);
    }
}
