package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPageObjectsTests extends TestBase {

    @Test
    void fillFormTest() {
        String firstName = "Дмитрий";
        String lastName = "Сапонов";
        String userEmail = "saponadmin@gmail.com";
        String gender = "Other";
        String userNumber = "8910123456";
        String currentAddress = "Россия, г. Москва";


        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openPage()
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserEmail(userEmail)
                        .setGender(gender)
                        .setUserNumber(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Computer Science");
        $(".subjects-auto-complete__menu").$(byText("Computer Science")).click();
        $("#subjectsInput").setValue("Maths");
        $(".subjects-auto-complete__menu").$(byText("Maths")).click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("59502-veneciya_italiya_zaliv_yasnyj_den.jpg");
        $("#currentAddress").setValue(currentAddress);
        $("#stateCity-wrapper").$(byText("Select State")).click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        // Выполняем проверки
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text("Male"),
                text(userNumber),
                text("01 March,1995"),
                text("Computer Science, Maths"),
                text("Sports, Reading"),
                text("59502-veneciya_italiya_zaliv_yasnyj_den.jpg"),
                text(currentAddress),
                text("Haryana Karnal")
        );
        $("#closeLargeModal").click();
    }
}
