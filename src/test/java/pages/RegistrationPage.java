package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ModalWindowWithResultTable;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private final CalendarComponent calendar = new CalendarComponent();
    private final ModalWindowWithResultTable modalWindow = new ModalWindowWithResultTable();
    private final SelenideElement firstNameInput = $("#firstName"),
                    lastNameInput = $("#lastName"),
                    userEmailInput = $("#userEmail"),
                    genderWrapper = $("#genterWrapper"),
                    userNumberInput = $("#userNumber"),
                    dayOfBirth = $("#dateOfBirthInput"),
                    subjectsInput = $("#subjectsInput"),
                    subjectsAutoCompleteMenu = $(".subjects-auto-complete__menu"),
                    hobbiesWrapper = $("#hobbiesWrapper"),
                    uploadPicture = $("#uploadPicture"),
                    currentAddress = $("#currentAddress"),
                    stateCityWrapper = $("#stateCity-wrapper"),
                    submit = $("#submit");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        // скрываем рекламу и подвал
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDayOfBirth(String day, String month, String year) {
        dayOfBirth.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value);
        subjectsAutoCompleteMenu.$(byText(value)).click();

        return this;
    }
    public RegistrationPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddress.setValue(value);

        return this;
    }

    public RegistrationPage selectStateCity(String state, String city) {
        stateCityWrapper.$(byText("Select State")).click();
        stateCityWrapper.$(byText(state)).click();
        stateCityWrapper.$(byText("Select City")).click();
        stateCityWrapper.$(byText(city)).click();

        return this;
    }

    public RegistrationPage pressSubmit() {
        submit.click();

        return this;
    }

    public RegistrationPage checkResultTable(String key, String value) {
        modalWindow.checkResultTable(key, value);

        return this;
    }

    public RegistrationPage checkModalTitle() {
        modalWindow.checkModalTitle();

        return this;
    }

    public RegistrationPage closeModal() {
        modalWindow.closeModal();

        return this;
    }

    public RegistrationPage checkModalVisible() {
        modalWindow.checkVisible();

        return this;
    }

    public RegistrationPage checkModalHidden() {
        modalWindow.checkHidden();

        return this;
    }
}
