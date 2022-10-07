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
import ru.yandex.praktikum.pageobjects.AuthPage;
import ru.yandex.praktikum.pageobjects.MainPage;
import ru.yandex.praktikum.pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.page;

public class AuthTests {

    private RegistrationPage registrationPage;
    private AuthPage authPage;
    private MainPage mainPage;
    private UserData registrationCorrectData;
    private UserData responseRegistration;
    private UserClient client = new UserClient();

    @Step("Initialization of test data")
    @Before
    public void createData() {
        registrationPage = page(RegistrationPage.class);
        authPage = page(AuthPage.class);
        mainPage = Selenide.page(MainPage.class);
        registrationCorrectData = new UserData(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphanumeric(10));
        responseRegistration = client.createRegistration(registrationCorrectData).then().extract().as(UserData.class);
    }

    @Step("Deleting test data after tests")
    @After
    public void deleteData() {
        if (responseRegistration.getAccessToken() != null) {
            String accessToken = responseRegistration.getAccessToken();
            client.deleteUser(accessToken);
        }
    }

    @Step("User authentication verification")
    @Test
    @DisplayName("Auth user from registration page")
    @Description("Should auth user from registration page")
    public void shouldAuthExistUserFromRegistrationPage() {
        registrationPage.openPage(UserClient.REGISTRATION_URL);
        registrationPage.clickSignUpButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        authPage.checkAuth();
    }

    @Step("User authentication verification")
    @Test
    @DisplayName("Auth user from main page")
    @Description("Should auth user from main page")
    public void shouldAuthExistUserFromMainPage() {
        mainPage.openPage(UserClient.BASE_URL);
        mainPage.clickLoginButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        authPage.checkAuth();
    }

    @Step("User authentication verification")
    @Test
    @DisplayName("Auth user from personal account")
    @Description("Should auth user from personal account")
    public void shouldAuthExistUserFromPersonalAccount() {
        mainPage.openPage(UserClient.BASE_URL);
        mainPage.clickPersonalAccountButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        authPage.checkAuth();
    }

    @Step("User authentication verification")
    @Test
    @DisplayName("Auth user from forgot password page")
    @Description("Should auth user from forgot password page")
    public void shouldAuthExistUserFromForgotPasswordPage() {
        authPage.openPage(UserClient.LOGIN_URL);
        authPage.clickRetrievePasswordButton();
        authPage.clickSignInButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        authPage.checkAuth();
    }
}
