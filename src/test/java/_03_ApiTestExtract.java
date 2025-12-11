
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

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

    @Test
    public void extractingJsonPath2() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

        int limit=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("meta.pagination.limit");

        Assert.assertTrue(limit==10);
    }


    @Test
    public void extractingJsonPath3() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // bütün id leri alınız ve içinde 8228890  olduğunu doğrulayınız

        ArrayList<Integer> idler=
                        given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.id");

        System.out.println("idler = " + idler);
        Assert.assertTrue(idler.contains(8228890));
    }

    @Test
    public void extractingJsonPath5() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // bütün name leri in içiden "Bhasvan Butt" değerinin geçtiğini
        // TestNg assertion ile doğrulayınız.


    }


}
