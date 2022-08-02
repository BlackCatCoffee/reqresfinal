package stepdefinitions;

import com.globallogic.reqresfinal.pojos.Book;
import com.globallogic.reqresfinal.pojos.Data;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.eventFrom;
import static org.testng.Assert.*;

import org.jetbrains.annotations.NotNull;
import resources.APIResources;
import resources.ResponseResource;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class StepDefinition extends Utils {
    Book book;
    APIResources currentResource;
    Map<String,Object> pages;
    ResponseSpecification respSpec;
    Response response;
    Integer userValue=0;
    TestDataBuild testDataBuild = new TestDataBuild();

    public StepDefinition() throws IOException {
    }

    @Given("user info of {string} and {string} with an id {int}")
    public void user_info_of_and_with_an_id(String name, String credential, Integer id) throws IOException {
        add_user_info_of_and(name, credential);
        the_user_has_an_id_of(id);
    }
    @Given("the user has an id of {int}")
    public void the_user_has_an_id_of(Integer id) throws IOException {
        user_Is_On_page(id);
    }

    @Given("user is on page {int}")
    public void user_Is_On_page(int pageNumber) throws IOException {
        req = given().spec(requestSpecification());
        userValue = pageNumber;
    }
    @Given("user registration of {string} and {string}")
    public void user_registration_of_and(String name, String password) throws IOException {
        Data user = testDataBuild.registerUser(name,password);
        req = given().auth().basic(user.getUserName(),user.getPassword()).spec(requestSpecification());

    }

    @Given("user info of {string} and {string}")
    public void add_user_info_of_and(String name, String credential) throws IOException {
        req = given().spec(requestSpecification()).body(testDataBuild.createUser(name,credential));
    }

    @When("user calls {string} with {string} http request with expected status {int}")
    public void user_calls_with_http_request_with_expected_status(String resource, @NotNull String method, int code) {
        currentResource = APIResources.valueOf(resource);
        System.out.println(currentResource.ordinal());
        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(code)
                .expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("CREATE")) {
            response = req
                    .when().post(currentResource.getResource());
//            pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String, Object>>() {});
//            System.out.println(pages.values());

        }
        else if(method.equalsIgnoreCase("GET")){
            if(userValue==0){
                response = req
                        .when().get(currentResource.getResource()).then().log().all().extract().response();
//                Map<String,Object> pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String, Object>>() {});
//               System.out.println(pages.values());
//                ValidatableResponse p = req.when().
//                        get(currentResource.getResource()).
//                        then().
//                        body("data.findAll { it.id < 6 }.first_name", hasItems("George", "Janet", "Emma", "Eve", "Charles"));
//                p.log().all();

            }else {
                response = req
                        .when().get(currentResource.getResource() + userValue).then().log().all().extract().response();
//                pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String, Object>>() {});
//                System.out.println(pages.values());
//                ValidatableResponse p = req.when().
//                        get(currentResource.getResource() + userValue).
//                        then().
//                        body("data.findAll { it.id > 6 }.first_name", hasItems("Michael","Lindsay","Tobias","George","Rachel"));
//                p.log().all();
                
            }
            //book = response.as(Book.class);
        }
        else if(method.equalsIgnoreCase("PUT")){
            response = req
                    .when().put(currentResource.getResource()+userValue);
//            pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String, Object>>() {});
//            System.out.println(pages.values());

        }
        else if(method.equalsIgnoreCase("PATCH")){
            response = req
                    .when().patch(currentResource.getResource()+userValue);
//            pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String, Object>>() {});
//            System.out.println(pages.values());
        }
        else if(method.equalsIgnoreCase("POST")){
            response = req
                    .when().post(currentResource.getResource());


        }
    }
//    @Then("the API call was successful")
//    public void the_api_call_was_successful() {
//        response = response
//                .then().spec(respSpec).extract().response();
//        pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String,Object>>() {});
//        System.out.println(pages.get("page").equals(2));
////        System.out.println(pages);
//        assertNotNull(response);
////        assertThat(pages.get("page"), equalTo(2));
//    }

    @Then("the {string} was {string}")
    public void the_was(String resource, String string2) {
        ResponseResource RR = ResponseResource.valueOf(resource);
        response = response
                .then().spec(respSpec).extract().response();
        pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String,Object>>() {});
        JsonPath js = new JsonPath(response.asString());
        System.out.println(js.get("page").toString());
        assertNotNull(js.get(RR.getResource()));
    }

    @Then("the {string} was {int}")
    public void the_was(String resource, Integer id) {
        ResponseResource RR = ResponseResource.valueOf(resource);
        response = response
                .then().spec(respSpec).extract().response();
        pages = req.get(currentResource.getResource()).as(new TypeRef<Map<String,Object>>() {});
        JsonPath js = new JsonPath(response.asString());
        assertEquals(js.get(RR.getResource()),id);
        //assertEquals();

    }


//    @After
//    public void after(){
//        APIResources.
//    }
}
