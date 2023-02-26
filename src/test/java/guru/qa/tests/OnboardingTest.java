package guru.qa.tests;

import guru.qa.pages.OnboardingPage;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class OnboardingTest extends TestBase {
    OnboardingPage onboardingPage = new OnboardingPage();

    @Test
    public void onboardingTest() {
        step("Check the page with languages", () -> {
            onboardingPage
                    .checkBasicElements("The Free Encyclopedia")
                    .checkButtonIsVisible(onboardingPage.addLangButton)
                    .goToNextPage();
        });

        step("Check the page with info about feed customization", () -> {
            onboardingPage
                    .checkBasicElements("New ways to explore")
                    .goToNextPage();
        });

        step("Check the page with info about sync", () -> {
            onboardingPage
                    .checkBasicElements("Reading lists with sync")
                    .goToNextPage();
        });

        step("Check the page with anonymous data", () -> {
            onboardingPage
                    .checkBasicElements("Send anonymous data")
                    .checkButtonIsVisible(onboardingPage.rejectToSendDataButton)
                    .checkButtonIsVisible(onboardingPage.acceptToSendDataButton);
        });
    }
}
