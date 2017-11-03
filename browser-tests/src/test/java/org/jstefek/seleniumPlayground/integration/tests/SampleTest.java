package org.jstefek.seleniumPlayground.integration.tests;

import java.io.File;
import org.jstefek.seleniumPlayground.integration.tests.model.Ticket;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends AbstractGoogleSearchTest {

    private static final String SUCCESS_MESSAGE = "Váš e-mail byl úspěšně odeslán. S odpovědí se Vám ozveme telefonicky nebo e-mailem co nejdříve.";

    @Test
    public void testFillingTMobileTicket() {
        String message = openGoogleSearchPage()
                .searchFor("t-mobile")
                .browseToFirstTMobileSite()
                .openSupportMenu()
                .openSupportChoicePage()
                .openContactForm()
                .fillInTicket(
                        new Ticket.Builder()
                                .setAttachment(new File(getClass().getClassLoader().getResource("cat.jpg").getFile()))
                                .setSendCopy(true)
                                .createTicket()
                )
                .submit()
                .getMessage();
        Assert.assertEquals(message, SUCCESS_MESSAGE);
    }
}
