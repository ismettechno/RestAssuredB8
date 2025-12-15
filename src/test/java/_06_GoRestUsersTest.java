import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _06_GoRestUsersTest {
    RequestSpecification reqSpec;
    Faker randomUreteci = new Faker();
    int UserId = 0;


    @BeforeClass
    public void Setup() {
        baseURI = "https://gorest.co.in";

        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer d7309fb1d0477b53483e5fd6f5678222b3412c0079ec2b388b4c23283879dfd9")
                .log(LogDetail.URI)
                .build();
    }

    @Test
    public void CreateUser() {
        String randomfullName= randomUreteci.name().fullName();
        String randomEmail= randomUreteci.internet().emailAddress();

        Map<String,String> newUser=new HashMap<>();
        newUser.put("name",randomfullName);
        newUser.put("gender","male");
        newUser.put("email",randomEmail);
        newUser.put("status","active");

        UserId= given()
                .spec(reqSpec)
                .body(newUser)

                .when()
                .post("/public/v2/users")

                .then()
                .statusCode(201)
                .log().body()
                .extract().path("id")
        ;

        System.out.println("UserId = " + UserId);
    }

    @Test(dependsOnMethods = "CreateUser")
    public void GetUserById() {
            given()
                    .spec(reqSpec)

                    .when()
                    .get("/public/v2/users/"+UserId)

                    .then()
                    .statusCode(200)
                    .body("id", equalTo(UserId))
            ;
    }

    @Test(dependsOnMethods = "GetUserById")
    public void UpdateUser() {


    }

    @Test(dependsOnMethods = "UpdateUser")
    public void DeleteUser() {


    }

    @Test(dependsOnMethods = "DeleteUserNegative")
    public void DeleteUserNegative() {


    }


}
