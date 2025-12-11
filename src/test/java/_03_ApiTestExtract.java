
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _03_ApiTestExtract {

    @Test
    public void extractingJsonPath() {

        String ulke=
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("country")
                ;

        System.out.println("ulke = " + ulke);
        Assert.assertEquals(ulke, "United States", "Ülke değeri beklenen değil");

    }


}
