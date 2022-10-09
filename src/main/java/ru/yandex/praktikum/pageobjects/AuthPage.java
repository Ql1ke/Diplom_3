package ru.yandex.praktikum.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthPage {

    @FindBy(how = How.XPATH, using = "//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input")
    private SelenideElement emailInputAlreadyRegistered;

    @FindBy(how = How.XPATH, using = "//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input")
    private SelenideElement passwordInputAlreadyRegistered;

    @FindBy(how = How.XPATH, using = "//*[text()='Войти']")
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Восстановить пароль']")
    private SelenideElement retrievePasswordButton;

    @Step("Open page")
    public void openPage(String url) {
        Selenide.open(url);
    }

    @Step("Set Email to input box")
    public void setEmailInputAlreadyRegisteredUser(String email) {
        emailInputAlreadyRegistered.setValue(email);
    }

    @Step("Set Password to input box")
    public void setPasswordInputAlreadyRegisteredUser(String password) {
        passwordInputAlreadyRegistered.setValue(password);
    }

    @Step("Click Sign In Button")
    public void clickSignInButton() {
        signInButton.scrollTo().click();
    }

    @Step("Authorization Check")
    public void checkAuth() {
        createOrderButton.shouldBe(Condition.visible);
    }

    @Step("Click Retrieve Password Button")
    public void clickRetrievePasswordButton() {
        retrievePasswordButton.click();
    }
}
