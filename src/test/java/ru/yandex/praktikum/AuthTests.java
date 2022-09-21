package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.BaseClient;
import ru.yandex.praktikum.model.Client;
import ru.yandex.praktikum.pageobjects.AuthPage;
import ru.yandex.praktikum.pageobjects.MainPage;
import ru.yandex.praktikum.pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.page;

public class AuthTests extends BaseClient{

    private RegistrationPage registrationPage;
    private AuthPage authPage;
    private MainPage mainPage;
    private Client registrationCorrectData;

    @Step("Initialization of test data")
    @Before
    public void createData(){
        registrationPage = page(RegistrationPage.class);
        authPage = page(AuthPage.class);
        mainPage = Selenide.page(MainPage.class);
        registrationCorrectData = new Client(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphanumeric(10));
        createClient(registrationCorrectData);
    }

    @Step("Deleting test data after tests")
    @After
    public void deleteClient(){
        deleteClient(registrationCorrectData);
        close();
    }

    @Step("User authentication verification")
    @Test
    @DisplayName("Auth user from registration page")
    @Description("Should auth user from registration page")
    public void shouldAuthExistUserFromRegistrationPage(){
        registrationPage.openPage(registrationURL);
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
    public void shouldAuthExistUserFromMainPage(){
        mainPage.openPage(BaseURL);
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
    public void shouldAuthExistUserFromPersonalAccount(){
        mainPage.openPage(BaseURL);
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
    public void shouldAuthExistUserFromForgotPasswordPage(){
        authPage.openPage(loginAPI);
        authPage.clickRetrievePasswordButton();
        authPage.clickSignInButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        authPage.checkAuth();
    }
}
