package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowWithResultTable {
    private final SelenideElement modalDialog = $(".modal-dialog"),
            modalTitle = $(".modal-title"),
            tableOfResult = $(".table-responsive"),
            closeModal = $("#closeLargeModal");

    public void checkVisible() {
        modalDialog.should(appear);
    }

    public void checkHidden() {
        modalDialog.should(hidden);
    }

    public void checkResultTable(String key, String value) {
        tableOfResult.$(byText(key)).parent().shouldHave(text(value));
    }

    public void checkModalTitle() {
        modalTitle.shouldHave(text("Thanks for submitting the form"));
    }

    public void closeModal() {
        closeModal.click();
    }
}
