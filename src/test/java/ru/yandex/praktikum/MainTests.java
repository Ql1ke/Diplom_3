package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.api.BaseClient;
import ru.yandex.praktikum.api.UserClientForMainTests;
import ru.yandex.praktikum.model.UserData;
import ru.yandex.praktikum.pageobjects.AuthPage;
import ru.yandex.praktikum.pageobjects.MainPage;
import ru.yandex.praktikum.pageobjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.close;

public class MainTests {

    private RegistrationPage registrationPage;
    private AuthPage authPage;
    private MainPage mainPage;
    private UserData registrationCorrectData;
    private UserClientForMainTests userClientForMainTests;
    private String token;


    @Step("Initialization of test data")
    @Before
    public void createData() {
        registrationPage = Selenide.page(RegistrationPage.class);
        authPage = Selenide.page(AuthPage.class);
        mainPage = Selenide.page(MainPage.class);
        userClientForMainTests = new UserClientForMainTests();
        registrationCorrectData = new UserData(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru", RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphanumeric(10));
        ValidatableResponse response = userClientForMainTests.create(registrationCorrectData);
        String rawToken = response.extract().path("accessToken");
        token = rawToken.replaceFirst("Bearer ", "");
    }

    @Step("Deleting test data after tests")
    @After
    public void deleteData() {
        userClientForMainTests.delete(token);
        close();
    }


    @Step("Checking the home page")
    @Test
    @DisplayName("Click personal account button")
    @Description("Should redirect to personal account")
    public void shouldRedirectToPersonalAccount() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickPersonalAccountButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        mainPage.checkPersonalAccount();
    }

    @Step("Checking the home page")
    @Test
    @DisplayName("Constructor from personal account")
    @Description("Should be able to click Constructor-button from personal account")
    public void shouldClickToConstructorFromPersonalAccount() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickPersonalAccountButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        mainPage.clickConstructorButton();
        mainPage.checkMainPage();
    }

    @Step("Checking the home page")
    @Test
    @DisplayName("Logo from personal account")
    @Description("Should be able to click Logo-button from personal account")
    public void shouldClickToLogoFromPersonalAccount() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickPersonalAccountButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());
        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        mainPage.clickLogo();
        mainPage.checkMainPage();
    }

    @Step("Checking the home page")
    @Test
    @DisplayName("Check logout button")
    @Description("Should be able to logout from personal account")
    public void shouldLogOutFromAccount() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickPersonalAccountButton();
        authPage.setEmailInputAlreadyRegisteredUser(registrationCorrectData.getEmail());

        authPage.setPasswordInputAlreadyRegisteredUser(registrationCorrectData.getPassword());
        authPage.clickSignInButton();
        mainPage.clickPersonalAccountButton();
        mainPage.clickLogoutButton();
        mainPage.checkLogout();
    }

    @Step("Checking the home page")
    @Test
    @DisplayName("Check Bun button")
    @Description("Should scroll to bun-menu")
    public void shouldBeBunMenu() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        mainPage.checkBun();
    }

    @Step("Checking the home page")
    @Test
    @DisplayName("Check Sauce button")
    @Description("Should scroll to Sauce-menu")
    public void shouldBeSauceMenu() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickSauceButton();
        mainPage.checkSauce();
    }

    @Step("Checking the home page")
    @Test
    @DisplayName("Check Filling button")
    @Description("Should scroll to Filling-menu")
    public void shouldBeFillingMenu() {
        mainPage.openPage(BaseClient.BASE_URL);
        mainPage.clickFillingButton();
        mainPage.checkFilling();
    }
}
