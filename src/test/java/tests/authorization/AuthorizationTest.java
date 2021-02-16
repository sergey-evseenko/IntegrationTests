package tests.authorization;

import adapters.AuthorizationAdapter;
import models.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AuthorizationTest extends BaseTest {

    User user = data.get("userForLogin.json", User.class);

    @Test(description = "Login with valid login/pass")
    public void login() {
        response = new AuthorizationAdapter().post(user, "clientId", "secret", 200);
        assertEquals(response.getEmail(), user.getEmail(), "Invalid email");
        assertNotNull(response.getAccessToken(), "Invalid access token");
    }

    @Test(description = "Login with refresh token")
    public void loginWithRefreshToken() {
        response = new AuthorizationAdapter().post(user, "clientId", "secret", 200);
        response = new AuthorizationAdapter().post(response.getRefreshToken());
        assertEquals(response.getEmail(), user.getEmail(), "Invalid email");
        assertNotNull(response.getAccessToken(), "Invalid access token");
    }

    @DataProvider(name = "List of invalid username/pass")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                //invalid username
                {"pollyisthebestqa11", "Nextnext!1", "ER2002", "Bad credentials", 404},
                //username: is null
                {null, "Nextnext!1", "ER2002", "Bad credentials", 404},
                //username: empty field
                {"", "Qwerty!123", "ER2002", "Bad credentials", 404},
                //invalid password
                {"pollyisthebestqa", "Nextnext!11", "ER2002", "Bad credentials", 401},
                //password: is null
                {"pollyisthebestqa", null, "ER2002", "Bad credentials", 401},
                //password: empty field
                {"pollyisthebestqa", "", "ER2002", "Bad credentials", 401},
                //blocked user
                {"qwerty8291", "Qwerty!12311", "ER2003", "User has been blocked", 401},
                //unconfirmed registration
                {"qwerty46856", "Qwerty!123", "ER2001", "You must confirm your registration. Check your email", 401}
        };
    }

    @Test(description = "validate username/pass", dataProvider = "List of invalid username/pass")
    public void validateCredentials(String userName, String password, String code, String description, int responseCode) {
        response = new AuthorizationAdapter().post(userName, password, responseCode);
        assertEquals(response.getType(), "AUTHENTICATION", "Invalid type");
        assertEquals(response.getCode(), code, "Invalid code");
        assertEquals(response.getDescription(), description, "Invalid description");
        //for avoid blocking user
        if (userName == user.getUserName()) {
            return;
        }
        new AuthorizationAdapter().post(user, "clientId", "secret", 200);
    }

    @DataProvider(name = "List of invalid clientId/clientSecret")
    public Object[][] invalidFormData() {
        return new Object[][]{
                {"backOfficeClientId", "secret", "Bad client credentials"},
                {"clientId", "backOfficeSecret", "Bad client credentials"},
                {"backOfficeClientId", "backOfficeSecret", "Wrong clientId for this url"}
        };
    }

    @Test(description = "validate clientId/clientSecret", dataProvider = "List of invalid clientId/clientSecret")
    public void validateFormData(String clientID, String clientSecret, String errorDescription) {
        response = new AuthorizationAdapter().post(user, clientID, clientSecret, 401);
        assertEquals(response.getError(), "invalid_client", "Invalid error message");
        assertEquals(response.getErrorDescription(), errorDescription, "Invalid error description");
    }
}
