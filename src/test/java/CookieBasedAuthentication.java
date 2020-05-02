import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;



public class CookieBasedAuthentication {


    private static final String CREATE_ISSUE = "/rest/api/2/issue";
    private static final String CREATE_SESSION = "/rest/auth/1/session";
    private static final String ADD_COMMENT ="rest/api/2/issue/{key}/comment";
    SessionFilter sessionFilter;
    static String newlyCreatedTicket = "OZ-29";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    public SessionFilter createSession(){
        sessionFilter = new SessionFilter();
        given().log().all().header("Content-Type","application/json;charset=UTF-8").body(Payload.getSessionPayload()).filter(sessionFilter)
                .when().post(CREATE_SESSION)
                .then().log().all();
        return sessionFilter;
    }

    @Test
    public void createIssue() {
        sessionFilter = createSession();
        String responseCreateTicket = given().log().all().header("Content-Type","application/json;charset=UTF-8").body(Payload.getCreateIssuePayload()).filter(sessionFilter)
                .when().post(CREATE_ISSUE)
                .then().log().all().extract().body().asString();

        JsonPath jsonPath = new JsonPath(responseCreateTicket);
        newlyCreatedTicket = jsonPath.getString("key");
    }


    @Test
    public void addCommentInExistingTicket() {
        sessionFilter=createSession();

        //use of path variable
        given().log().all().header("Content-Type","application/json;charset=UTF-8").pathParam("key",newlyCreatedTicket).body(Payload.getAddCommentPayload()).filter(sessionFilter)
                .when().post(ADD_COMMENT)
                .then().log().all();

    }






}
