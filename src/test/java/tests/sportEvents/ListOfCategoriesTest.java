package tests.sportEvents;

import models.Categories;
import models.Category;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ListOfCategoriesTest extends BaseTest {

    @Test(description = "get list of categories")
    public void getListOfCategories() {
        Category category = data.get("category.json", Category.class);
        Categories categories = catalogAdapter.getCategories("de", "1");
        assertEquals(categories.getCategories()[0], category, "Invalid list of categories");
        assertTrue(categories.getMarketLayers().length > 0, "Invalid list of market layers");
    }

    @DataProvider(name = "params")
    public Object[][] params() {
        return new Object[][]{
                //langIso is en
                {"en", "1", 200, ""},
                //empty langIso
                {"", "1", 200, ""},
                //invalid langIso
                {"qwerty", "1", 200, ""},
                //langIso is null
                {null, "1", 400, "Required String parameter 'langIso' is not present"},
                //empty sportId
                {"en", "", 400, "Required Long parameter 'sportId' is not present"},
                //sportId is invalid
                {"en", "qwerty", 400, "Type mismatch."},
                //sportId is null
                {"en", null, 400, "Required Long parameter 'sportId' is not present"},
        };
    }

    @Test(description = "validate params", dataProvider = "params")
    public void validateParams(String langIso, String sportId, int expectedStatusCode, String responseMessage) {

        responseBetOn = catalogAdapter.getCategories(langIso, sportId, expectedStatusCode);
        if (expectedStatusCode == 400) {
            assertEquals(responseBetOn.getMessage(), responseMessage, "Invalid response");
        }
    }


}
