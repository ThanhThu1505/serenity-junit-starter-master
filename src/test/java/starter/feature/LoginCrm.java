package starter.feature;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.questions.Text;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginCrm {
    @CastMember(name = "Toby")
    Actor toby;

    @Test
    @DisplayName("Login CRM system")
    void addToEmptyList() throws InterruptedException {
        toby.attemptsTo(
                Open.url("https://crm.anhtester.com/admin/authentication"),
                Enter.theValue("admin@example.com").into("//input[@id='email']"),
                Enter.theValue("123456").into("#password").thenHit(Keys.RETURN),
                Click.on("//span[contains(text(),'Customers')]"),
                Enter.theValue("CMC").into("//input[@type='search' and @class='form-control input-sm']")
        );
        Thread.sleep(3 * 1000);

        var todos = toby.asksFor(Text.ofEach("//tr/td[@class='sorting_1']/a"));
        assertThat(todos).contains("CMC");

        Thread.sleep(5 * 1000);
    }
}
