import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("places", request.session().attribute("places"));
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/places", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      ArrayList<Places> places = request.session().attribute("places");

      if (places == null) {
        places = new ArrayList<Places>();
        request.session().attribute("places", places);
      }
      String city = request.queryParams("city");
      String country = request.queryParams("country");
      Places myPlaces = new Places(city, country);

      places.add(myPlaces);
      model.put("places", request.session().attribute("places"));
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
