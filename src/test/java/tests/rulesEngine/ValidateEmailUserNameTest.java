package tests.rulesEngine;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class ValidateEmailUserNameTest extends BaseTest {

    @DataProvider(name = "Params")
    public Object[][] params() {
        return new Object[][]{
                //uniq email
                {"email", "e", "ytrewq37538@gmail.com", null},
                //not uniq email
                {"email", "e", "qwerty81@gmail.com", "E0005"},
                //uniq username
                {"username", "u", "polinazz123456", null},
                //not uniq username
                {"username", "u", "polinazz", "E0005"}
        };
    }

    @Test(description = "validate params", dataProvider = "Params")
    public void validateParams(String path, String param, String paramValue, String code) {
        responseBetOn = rulesAdapter.validate(path, param, paramValue);
        if (path.equals("email")) {
            assertEquals(responseBetOn.getEmail(), code, "Invalid response message");
        }
        if (path.equals("username")) {
            assertEquals(responseBetOn.getUserName(), code, "Invalid response message");
        }
    }
}
