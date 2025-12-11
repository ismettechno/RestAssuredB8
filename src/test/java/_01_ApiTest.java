import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_ApiTest {

    @Test
    public void Test1()
    {
        // Postman
        // 1- Endpoint i çağırmadna önce hazırlıkların yapıldığı bölüm : Request, gidecek body, token
        // 2- Endpoint in çağrıldığı bölüm  : Endpoint in çağrılması(METOD: GET,POST ..)
        // 3- Endpoint çağrıldıktan sonraki bölüm : Response, Test(Assert), data

        given()
                //1.bölümlerle ilgili işler : giden body,token
                .when()
                //2.bölümlerle ilgili işler : gidiş metodu , endpoint, apinin çağrılma kısmı
                .then()
                //3.bölümlerle ilgili işler: gelen data, assert,test
        ;

    }

    @Test
    public void statusCodeTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()   // dönüş datasını gösterir  all: bütün bilgiler
                .statusCode(200)  // dönen status kod 200 e eşitmi, assert
        ;

    }

    @Test
    public void contentTypeTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()   // dönüş datasını gösterir  all: bütün bilgiler
                .contentType(ContentType.JSON)  // donen içerik formatı JSON MI assert
        ;
    }

    @Test
    public void checkCountryInResponseBody()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()   // dönüş datasını gösterir  all: bütün bilgiler
                .contentType(ContentType.JSON)  // donen içerik formatı JSON MI assert
                .body("country", equalTo("United States"))

        // country yi dışarı almadan
        // bulundu yeri (path i) vererek içerde assertion yapıyorum.
        // Bunu hamcrest kütüphanesi yapıyor
        ;
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // places dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız

        given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")
                .then()
                .log().body()
                .body("places[2].'place name'", equalTo("Dörtağaç Köyü")) // 2 indexdeki eleman buna eşit mi
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))  // tum placelerde bu eleman var mı
        ;
    }

    @Test
    public void bodyArrayHasSizeTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places", hasSize(1)) // bu dizi size 1 mi
        ;
    }

    @Test
    public void combiningTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .statusCode(200) // assert
                .contentType(ContentType.JSON)  // assert
                .body("places", hasSize(1)) // assert
                .body("places.'place name'", hasItem("Beverly Hills")) // assert
                .body("country", equalTo("United States")) // assert
        ;
    }




}
