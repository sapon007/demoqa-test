package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StudentRegistrationFormPageObjectsTests extends RemoteTestBase {

    // Test data
    String firstName = "Дмитрий";
    String lastName = "Сапонов";
    String userEmail = "saponadmin@gmail.com";
    String gender = "Other";
    String userNumber = "8910123456";
    String currentAddress = "Россия, г. Москва";
    String dayOfBirth = "01";
    String monthOfBirth = "March";
    String yearOfBirth = "1995";
    String firstSubject = "Computer Science";
    String secondSubject = "Maths";
    String firstHobby = "Sports";
    String secondHobby = "Reading";
    String thirdHobby = "Music";
    String img = "59502-veneciya_italiya_zaliv_yasnyj_den.jpg";
    String state = "Haryana";
    String city = "Karnal";

    RegistrationPage registrationPage = new RegistrationPage();

    @Tag("Selenoid_run")
    @DisplayName("Заполнить все поля формы")
    @Test
    void fillInAllFieldsOfTheFormTest() {

        step("Заполняем форму", ()-> {
            registrationPage.openPage()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .setDayOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                    .setSubjects(firstSubject)
                    .setSubjects(secondSubject)
                    .setHobbies(firstHobby)
                    .setHobbies(secondHobby)
                    .setHobbies(thirdHobby)
                    .uploadPicture(img)
                    .setCurrentAddress(currentAddress)
                    .selectStateCity(state, city)
                    .pressSubmit();
        });


        // Выполняем проверки
        step("Выполняем проверки", ()-> {
            registrationPage.checkModalVisible()
                    .checkModalTitle()
                    .checkResultTable("Student Name", firstName + " " + lastName)
                    .checkResultTable("Student Email", userEmail)
                    .checkResultTable("Gender", gender)
                    .checkResultTable("Mobile", userNumber)
                    .checkResultTable("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                    .checkResultTable("Address", currentAddress)
                    .closeModal()
                    .checkModalHidden();
        });

    }

    @Tag("Selenoid_run")
    @DisplayName("Заполнить только обязательные поля формы")
    @Test
    void fillInOnlyRequiredFieldsOfTheForm() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(userNumber)
                .pressSubmit();

        registrationPage.checkModalVisible()
                .checkModalTitle()
                .checkResultTable("Student Name", firstName + " " + lastName)
                .checkResultTable("Gender", gender)
                .checkResultTable("Mobile", userNumber)
                .closeModal()
                .checkModalHidden();

    }

    @Tag("Selenoid_run")
    @DisplayName("Не заполнить ни одно поле и отправить форму")
    @Test
    void notFillFieldsOfTheForm() {

        registrationPage.openPage()
                .pressSubmit();

        registrationPage.checkModalHidden();
    }
}
