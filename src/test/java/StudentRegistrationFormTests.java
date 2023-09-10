import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        // скрываем рекламу и подвал
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Дмитрий");
        $("#lastName").setValue("Сапонов");
        $("#userEmail").setValue("saponadmin@gmail.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("89101234567");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--001").click();
        // To do
        $("subjects-auto-complete__control").click();

        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#currentAddress").setValue("Moscow, Russia");
        $("#submit").click();
    }
}
