package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class StudentRegistrationFormPageObjectsTests extends TestBase {

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
    String state = "Haryana";
    String city = "Karnal";

    @DisplayName("Заполнить все поля формы")
    @Test
    void fillInAllFieldsOfTheFormTest() {

        RegistrationPage registrationPage = new RegistrationPage();
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
                        .uploadPicture("59502-veneciya_italiya_zaliv_yasnyj_den.jpg")
                        .setCurrentAddress(currentAddress)
                        .selectStateCity(state, city)
                        .pressSubmit();

        // Выполняем проверки
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
    }

    @DisplayName("Заполнить только обязательные поля формы")
    @Test
    void fillInOnlyRequiredFieldsOfTheForm() {
        RegistrationPage registrationPage = new RegistrationPage();

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

    @DisplayName("Не заполнить ни одно поле и отправить форму")
    @Test
    void notFillFieldsOfTheForm() {
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.openPage()
                .pressSubmit();

        registrationPage.checkModalHidden();
    }
}
