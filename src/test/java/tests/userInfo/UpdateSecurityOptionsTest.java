package tests.userInfo;

import models.SecurityOption;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class UpdateSecurityOptionsTest extends BaseTest {

    @DataProvider(name = "Invalid security options")
    public Object[][] passwords() {
        return new Object[][]{
                //question id is 0
                {"QA Automation", 0, "secretQuestionId", "ER0006", "Choose the value"},
                //question id is invalid
                {"QA Automation", 6, "secretQuestionId", "ER0009", "Incorrect value"},
                //TODO empty question id
                //{"QA Automation", "", "secretQuestionId", "ER0004", "Field is mandatory"}
                //answer is empty
                {"", 3, "secretAnswer", "ER0007", "Wrong value size"},
                //answer is too long
                {faker.lorem().characters(51), 3, "secretAnswer", "ER0007", "Wrong value size"},
                //answer is null
                {null, 3, "secretAnswer", "ER0004", "Field is mandatory"}
        };
    }

    @Test(description = "validate security options", dataProvider = "Invalid security options")
    public void validateSecurityOptions(String question, int questionId, String field, String code, String description) {
        SecurityOption securityOption = new SecurityOption(question, questionId);
        responseBetOn = userAdapter.putSecurityOption(securityOption, 400);
        assertEquals(responseBetOn.getField(), field, "Invalid field");
        assertEquals(responseBetOn.getType(), "VALIDATION", "Invalid type");
        assertEquals(responseBetOn.getCode(), code, "Invalid code");
        assertEquals(responseBetOn.getDescription(), description, "Invalid description");
    }

    @Test(description = "update security options")
    public void updateSecurityOptions() {
        SecurityOption securityOption = new SecurityOption(faker.lorem().characters(10), faker.number().numberBetween(1, 5));
        userAdapter.putSecurityOption(securityOption, 200);
        SecurityOption actualSecurityOption = userAdapter.getSecurityOption();
        assertEquals(actualSecurityOption, securityOption, "Invalid security option");
    }
}
