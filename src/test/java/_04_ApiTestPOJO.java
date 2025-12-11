
import Model.ZipCode;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _04_ApiTestPOJO {


    @Test
    public void extractJsonAll_POJO() {

        ZipCode adres1=
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .extract().body().as(ZipCode.class)
        ;  // Tüm body all Location.class (kalıba göre) çevir

        System.out.println("adres1.getCountry() = " + adres1.getCountry());
        System.out.println("adres1.getPlaces() = " + adres1.getPlaces());

    }

}
