package tests.template;

import adapters.TemplateAdapter;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class templateTest extends BaseTest {

    String templateUrl = "https://sdk1-beton.ngsb-rc.com/sdk/service/v1/bet-on/template/registration";

    @Test(description = "getTemplateLink")
    public void getTemplateLink() {
        responseBody = new TemplateAdapter().getTemplateUrl(token, 1, 200);
        assertEquals(responseBody.getTemplateUrl(), templateUrl, "invalid template url");
        assertNotEquals(responseBody.getJwtToken(), null, "Invalid jwt token");

    }

    @Test(description = "invalid providerId")
    public void invalidProviderId() {
        int providerId = 1234;
        responseBody = new TemplateAdapter().getTemplateUrl(token, providerId, 400);
        assertEquals(responseBody.getMessage(), "Incorrect Provider ID=" + providerId, "invalid provider id error");
    }

    @Test(description = "missed providerId")
    public void missedProviderId() {
        responseBody = new TemplateAdapter().getTemplateUrl(token, 400);
        assertEquals(responseBody.getMessage(), "Required Long parameter 'providerId' is not present", "missed provider id error");
    }


}