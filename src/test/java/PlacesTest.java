import org.junit.*;
import static org.junit.Assert.*;

public class PlacesTest {

  @Test
  public void Places_instantiatesCorrectly_true() {
    Places myPlaces = new Places("Paris", "France");
    assertEquals(true, myPlaces instanceof Places);
  }

  @Test
  public void Places_instantiatesWithCity_true() {
    Places myPlaces = new Places ("Paris", "France");
    assertEquals("Paris", myPlaces.getCity());
  }
}
