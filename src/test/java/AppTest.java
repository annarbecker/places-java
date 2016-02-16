import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Enter a place");
  }

  @Test
  public void createPlace() {
    goTo("http://localhost:4567/");
    fill("#city").with("Rome");
    fill("#country").with("Italy");
    submit(".btn");
    assertThat(pageSource()).contains("Rome", "Italy");
  }

  @Test
  public void displaysMultiplePlaces() {
    goTo("http://localhost:4567/");
    fill("#city").with("Rome");
    fill("#country").with("Italy");
    submit(".btn");
    fill("#city").with("Paris");
    fill("#country").with("France");
    submit(".btn");
    assertThat(pageSource()).contains("Rome", "Italy", "Paris", "France");
  }
}
