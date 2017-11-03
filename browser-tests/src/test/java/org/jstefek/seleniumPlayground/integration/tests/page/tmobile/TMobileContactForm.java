package org.jstefek.seleniumPlayground.integration.tests.page.tmobile;

import java.io.File;
import org.jstefek.seleniumPlayground.integration.tests.model.Ticket;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TMobileContactForm extends AbstractPage {

    @WaitForVisibilityAfterLoad
    @FindBy(name = "subject")
    private WebElement subjectInput;
    @WaitForVisibilityAfterLoad
    @FindBy(name = "content")
    private WebElement contentInput;
    @FindBy(name = "phoneNumber")
    private WebElement phoneNumberInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "sendCopy")
    private WebElement sendCopyCheckBox;
    @FindBy(xpath = "//div[contains(@class, 'checkbox')]/label[.//input[@name='sendCopy']]")
    private WebElement sendCopyCheckBoxSpan;
    @FindBy(css = ".select-files-container + input[type=file]")
    private WebElement attachmentInput;
    @WaitForVisibilityAfterLoad
    @FindBy(name = "submit")
    private WebElement submitButton;
    @FindBy(className = "portlet-msg-success")
    private WebElement message;

    public TMobileContactForm(WebDriver browser, PageFactory pageFactory) {
        super(browser, pageFactory);
    }

    public TMobileContactForm fillInTicket(Ticket t) {
        fillInSubject(t.getSubject());
        fillInContent(t.getContent());
        fillInPhoneNumber(t.getPhoneNumber());
        fillInEmail(t.getEmail());
        setSendCopyValue(t.isSendCopy());
        if (t.getAttachment() != null) {
            setAttachment(t.getAttachment());
        }
        return this;
    }

    public TMobileContactForm fillInContent(String text) {
        getRequestGuard().guardAjax(contentInput).sendKeys(text);
        return this;
    }

    public TMobileContactForm fillInSubject(String text) {
        getRequestGuard().guardAjax(subjectInput).sendKeys(text);
        return this;
    }

    public TMobileContactForm fillInPhoneNumber(String text) {
        phoneNumberInput.sendKeys(text);
        return this;
    }

    public TMobileContactForm fillInPhoneNumber(int number) {
        return fillInPhoneNumber(String.valueOf(number));
    }

    public TMobileContactForm fillInEmail(String text) {
        emailInput.sendKeys(text);
        return this;
    }

    public TMobileContactForm setSendCopyValue(boolean value) {
        if (sendCopyCheckBox.isSelected() != value) {
            sendCopyCheckBoxSpan.click();
        }
        return this;
    }

    public TMobileContactForm setAttachment(String file) {
        getRequestGuard().guardAjax(attachmentInput).sendKeys(file);
        return this;
    }

    public TMobileContactForm setAttachment(File f) {
        return setAttachment(f.getAbsolutePath());
    }

    public String getMessage() {
        return message.getText().trim();
    }

    public TMobileContactForm submit() {
        submitButton.click();
        return createPage(this.getClass());
    }

}
