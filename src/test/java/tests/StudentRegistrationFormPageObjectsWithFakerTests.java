package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class StudentRegistrationFormPageObjectsWithFakerTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();


    @DisplayName("Заполнить все поля формы")
    @Test
    void fillInAllFieldsOfTheFormTest() {

        registrationPage.openPage()
                        .setFirstName(testData.firstName)
                        .setLastName(testData.lastName)
                        .setUserEmail(testData.userEmail)
                        .setGender(testData.gender)
                        .setUserNumber(testData.userNumber)
                        .setDayOfBirth(testData.dayOfBirth, testData.monthOfBirth, testData.yearOfBirth)
                        .setSubjects(testData.firstSubject)
                        .setSubjects(testData.secondSubject)
                        .setHobbies(testData.firstHobby)
                        .setHobbies(testData.secondHobby)
                        .setHobbies(testData.thirdHobby)
                        .uploadPicture(testData.img)
                        .setCurrentAddress(testData.currentAddress)
                        .selectStateCity(testData.state, testData.city)
                        .pressSubmit();

        // Выполняем проверки
        registrationPage.checkModalVisible()
                        .checkModalTitle()
                        .checkResultTable("Student Name", testData.firstName + " " + testData.lastName)
                        .checkResultTable("Student Email", testData.userEmail)
                        .checkResultTable("Gender", testData.gender)
                        .checkResultTable("Mobile", testData.userNumber)
                        .checkResultTable("Date of Birth", testData.dayOfBirth + " "
                                + testData.monthOfBirth + "," + testData.yearOfBirth)
                        .checkResultTable("Address", testData.currentAddress)
                        .closeModal()
                        .checkModalHidden();
    }

    @DisplayName("Заполнить только обязательные поля формы")
    @Test
    void fillInOnlyRequiredFieldsOfTheForm() {

        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.userNumber)
                .pressSubmit();

        registrationPage.checkModalVisible()
                .checkModalTitle()
                .checkResultTable("Student Name", testData.firstName + " " + testData.lastName)
                .checkResultTable("Gender", testData.gender)
                .checkResultTable("Mobile", testData.userNumber)
                .closeModal()
                .checkModalHidden();

    }

    @DisplayName("Не заполнить ни одно поле и отправить форму")
    @Test
    void notFillFieldsOfTheForm() {

        registrationPage.openPage()
                .pressSubmit();

        registrationPage.checkModalHidden();
    }
}
