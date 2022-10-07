package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.UserClient;
import ru.yandex.praktikum.model.UserData;
import ru.yandex.praktikum.pageobjects.RegistrationPage;

public class RegistrationTest {

    private RegistrationPage registrationPage;
    private UserData registrationCorrectData;
    private UserData registrationInCorrectData;
    private UserClient client = new UserClient();

    @Step("Initialization of test data")
    @Before
    public void createData() {
        registrationPage = Selenide.page(RegistrationPage.class);
        registrationCorrectData = new UserData(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphanumeric(10));
        registrationInCorrectData = new UserData(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphanumeric(5));
    }

    @Step("Deleting test data after tests")
    @After
    public void deleteUser() {
        UserData responseAuthorization = client.createAuthorization(registrationCorrectData).then().extract().as(UserData.class);
        if (responseAuthorization.getAccessToken() != null) {
            String accessToken = responseAuthorization.getAccessToken();
            client.deleteUser(accessToken);
        }
    }


    @Step("Checking Registration")
    @Test
    @DisplayName("Registration new user with correct password")
    @Description("Should create new user")
    public void shouldCreateNewUserWithCorrectPassword() {
        registrationPage.openPage(UserClient.REGISTRATION_URL);
        registrationPage.setNameInput(registrationCorrectData.getName());
        registrationPage.setEmailInput(registrationCorrectData.getEmail());
        registrationPage.setPasswordInput(registrationCorrectData.getPassword());
        registrationPage.clickRegisterButton();
        registrationPage.checkRegistration();
    }

    @Step("Checking Registration")
    @Test
    @DisplayName("Registration new user with incorrect password (less 6 symbols)")
    @Description("Should be error with short password")
    public void shouldBeErrorWithBadPassword() {
        registrationPage.openPage(UserClient.REGISTRATION_URL);
        registrationPage.setNameInput(registrationInCorrectData.getName());
        registrationPage.setEmailInput(registrationInCorrectData.getEmail());
        registrationPage.setPasswordInput(registrationInCorrectData.getPassword());
        registrationPage.clickRegisterButton();
        registrationPage.checkPassword();
    }

}
