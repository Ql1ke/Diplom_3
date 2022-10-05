package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.yandex.praktikum.model.UserData;

import static io.restassured.RestAssured.given;

public class UserClientForMainTests {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private final String USER_REGISTER_PATH = "/api/auth/register";

    private final String USER_LOGIN_PATH = "/api/auth/login";

    private final String USER_PATH = "/api/auth/user";

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Create new user {user}")
    public ValidatableResponse create(UserData user) {
        return given()
                .spec(getBaseSpec())
                .when()
                .body(user)
                .post(USER_REGISTER_PATH)
                .then();
    }

    @Step("Login user with credentials {credentials}")
    public ValidatableResponse login(UserData credentials) {
        return given()
                .spec(getBaseSpec())
                .when()
                .body(credentials)
                .post(USER_LOGIN_PATH)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse delete(String token) {

        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .when()
                .delete(USER_PATH)
                .then();
    }
}
