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



    }


}
