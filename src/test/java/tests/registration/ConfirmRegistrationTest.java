package tests.registration;

import adapters.ConfirmRegistrationAdapter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class ConfirmRegistrationTest extends BaseTest {

    @DataProvider(name = "invalid confirmation codes")
    public Object[][] registrationCodes() {
        return new Object[][]{
                //TODO: valid registration code
                //{"CeeB4Qp*Eh", "ER3001", "Body is correct", 200},
                //invalid code
                {"12345", "ER3001", "Code not found", 400},
                //code is null
                {null, "ER3001", "Code not found", 400},
        };
    }

    @Test(description = "validate confirmation code", dataProvider = "invalid confirmation codes")
    public void validateConfirmationCode(String registrationCode, String code, String errorDescription, int responseCode) {
        responseBetOn = new ConfirmRegistrationAdapter().getConfirmRegistration(registrationCode, responseCode);
        assertEquals(responseBetOn.getField(), "Confirmation code", "Invalid field");
        assertEquals(responseBetOn.getType(), "CONFIRMATION", "Invalid type");
        assertEquals(responseBetOn.getCode(), code, "Invalid code");
        assertEquals(responseBetOn.getDescription(), errorDescription, "Invalid description");
    }
}
