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

}
