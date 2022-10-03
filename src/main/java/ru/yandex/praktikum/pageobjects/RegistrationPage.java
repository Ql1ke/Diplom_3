package ru.yandex.praktikum.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {

    @FindBy(how = How.XPATH, using = "//*[text()='Имя']/following-sibling::*")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = "//*[text()='Email']/following-sibling::*")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/following-sibling::*")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//*[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Вход']")
    private SelenideElement singInButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Некорректный пароль']")
    private SelenideElement errorPassword;

    @FindBy(how = How.XPATH, using = "//*[text()='Уже зарегистрированы?']/child::*")
    private SelenideElement singUpButton;

    @Step("Open page")
    public void openPage(String url) {
        Selenide.open(url);
    }

    @Step("Set name to input box")
    public void setNameInput(String name) {
        nameInput.setValue(name);
    }

    @Step("Set email to input box")
    public void setEmailInput(String email) {
        emailInput.setValue(email);
    }

    @Step("Set password to input box")
    public void setPasswordInput(String password) {
        passwordInput.setValue(password);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Click sign up button")
    public void clickSignUpButton() {
        singUpButton.click();
    }

    @Step("Verify registration")
    public void checkRegistration() {
        singInButton.shouldBe(Condition.visible);
    }

    @Step("Verify password")
    public void checkPassword() {
        errorPassword.shouldBe(Condition.visible);
    }
}
