package ru.yandex.praktikum.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;


public class MainPage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/header/nav/a/p")
    private SelenideElement personalAccountButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Булки']")
    private SelenideElement bunButton;

    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab_type_current__2BEPc'>span]")
    private SelenideElement currentSection;

    @FindBy(how = How.XPATH, using = "//*[text()='Соусы']")
    private SelenideElement sauceButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Начинки']")
    private SelenideElement fillingButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button")
    private SelenideElement logoutButton;

    @FindBy(how = How.XPATH, using = "//*[text()='В этом разделе вы можете изменить свои персональные данные']")
    private SelenideElement accountPersonalText;

    @FindBy(how = How.XPATH, using = "//*[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement siteLogo;

    @FindBy(how = How.XPATH, using = "//div[@class='Auth_login__3hAey']/h2")
    private SelenideElement loginText;

    @Step("Open Page")
    public void openPage(String url) {
        Selenide.open(url);
    }

    @Step("Accessing your personal account")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Click Constructor Button")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Click Bun Button")
    public MainPage clickBunButton() {
        bunButton.shouldBe(enabled).doubleClick();
        return this;
    }

    public boolean isBunActive() {
        return currentSection.getText().contains("Булки");
    }

    @Step("Click Sause Button")
    public MainPage clickSauceButton() {
        sauceButton.shouldBe(enabled).doubleClick();
        return this;
    }

    public boolean isSauceActive() {
        return currentSection.getText().contains("Соусы");
    }

    @Step("Click Filling Button")
    public MainPage clickFillingButton() {
        fillingButton.shouldBe(enabled).doubleClick();
        return this;
    }

    public boolean isFillingActive() {
        return currentSection.getText().contains("Начинки");
    }

    @Step("Click Login Button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Click Logout Button")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Verify personal account")
    public void checkPersonalAccount() {
        accountPersonalText.shouldBe(Condition.visible);
    }

    @Step("Verify main page")
    public void checkMainPage() {
        createOrderButton.shouldBe(Condition.visible);
    }

    @Step("Click Logo Button")
    public void clickLogo() {
        siteLogo.click();
    }

    @Step("Verify logout")
    public void checkLogout() {
        loginText.shouldBe(Condition.visible);
    }

}
