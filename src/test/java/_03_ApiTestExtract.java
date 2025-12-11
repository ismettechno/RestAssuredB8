
import io.restassured.response.Response;
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
        // bütün name leri in içinde "Bhasvan Butt" değerinin geçtiğini
        // TestNg assertion ile doğrulayınız.

        ArrayList<String> nameler=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.name");

        System.out.println("idler = " + nameler);
        Assert.assertTrue(nameler.contains("Bhasvan Butt"));
    }

    @Test
    public void extractingJsonPathResponsAll() {

        Response donenBody=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().response();  // bütün dönen bady alındı

        ArrayList<Integer> idler=donenBody.path("data.id");
        ArrayList<String> nameler=donenBody.path("data.name");

        int limit=  donenBody.path("meta.pagination.limit");

        System.out.println("limit = " + limit);
        System.out.println("idler = " + idler);
        System.out.println("nameler = " + nameler);

        Assert.assertTrue(nameler.contains("Bhasvan Butt"));
        Assert.assertTrue(idler.contains(8228890));
        Assert.assertTrue(limit==10);
    }


}
