package tests.sportEvents;

import models.OutrightCategory;
import models.OutrightCompetitor;
import models.OutrightTournament;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OutrightTest extends BaseTest {
    OutrightCompetitor[] outrightCompetitorList;
    OutrightTournament[] outrightTournamentsList;
    OutrightCategory[] outrightCategoryList;

    @Test(description = "get available outright for competitors (EN)")
    public void getOutrightForCompetitorsEn() {
        //for english (en)
        outrightCompetitorList = outrightAdapter.getOutright("1", "en", "competitors", OutrightCompetitor[].class);
        assertTrue(outrightCompetitorList.length > 0, "List of outright for competitors is empty");
    }

    @Test(description = "get available outright for competitors (invalid lang)")
    public void getOutrightForCompetitorsInvalidLang() {
        //for invalid language
        outrightCompetitorList = outrightAdapter.getOutright("1", "qwerty", "competitors", OutrightCompetitor[].class);
        assertTrue(outrightCompetitorList.length > 0, "List of outright for competitors is empty");
    }

    @Test(description = "get available outright for competitors (DE)")
    public void getOutrightForCompetitorsDe() {
        //for german (de)
        outrightCompetitorList = outrightAdapter.getOutright("1", "de", "competitors", OutrightCompetitor[].class);
        assertTrue(outrightCompetitorList.length > 0, "List of outright for competitors is empty");
    }

    @Test(description = "get available outright for competitors (RU)")
    public void getOutrightForCompetitorsRu() {
        //for russian (ru)
        outrightCompetitorList = outrightAdapter.getOutright("1", "ru", "competitors", OutrightCompetitor[].class);
        assertTrue(outrightCompetitorList.length > 0, "List of outright for competitors is empty");
    }

    @Test(description = "get available outright for tournaments (EN)")
    public void getOutrightForTournamentsEN() {
        //for english (en)
        outrightTournamentsList = outrightAdapter.getOutright("25", "en", "tournament", OutrightTournament[].class);
        assertTrue(outrightTournamentsList.length > 0, "List of outright for tournaments is empty");
    }

    @Test(description = "get available outright for tournaments (invalid lang)")
    public void getOutrightForTournamentsInvalidLang() {
        //for invalid language
        outrightTournamentsList = outrightAdapter.getOutright("25", "qwerty", "tournament", OutrightTournament[].class);
        assertTrue(outrightTournamentsList.length > 0, "List of outright for tournaments is empty");
    }

    @Test(description = "get available outright for tournaments (DE)")
    public void getOutrightForTournamentsDe() {
        //for german (de)
        outrightTournamentsList = outrightAdapter.getOutright("25", "de", "tournament", OutrightTournament[].class);
        assertTrue(outrightTournamentsList.length > 0, "List of outright for tournaments is empty");
    }

    @Test(description = "get available outright for tournaments (RU)")
    public void getOutrightForTournamentsRu() {
        //for russian (ru)
        outrightTournamentsList = outrightAdapter.getOutright("25", "ru", "tournament", OutrightTournament[].class);
        assertTrue(outrightTournamentsList.length > 0, "List of outright for tournaments is empty");
    }

    @Test(description = "get available outright for categories (EN)")
    public void getOutrightForCategoryEN() {
        //for english (en)
        outrightCategoryList = outrightAdapter.getOutright("27", "en", "category", OutrightCategory[].class);
        assertTrue(outrightCategoryList.length > 0, "List of outright for categories is empty");
    }

    @Test(description = "get available outright for categories (invalid lang)")
    public void getOutrightForCategoryInvalidLang() {
        //for invalid language
        outrightCategoryList = outrightAdapter.getOutright("27", "qwerty", "category", OutrightCategory[].class);
        assertTrue(outrightCategoryList.length > 0, "List of outright for categories is empty");
    }

    @Test(description = "get available outright for categories (DE)")
    public void getOutrightForCategoryDE() {
        //for german (de)
        outrightCategoryList = outrightAdapter.getOutright("27", "de", "category", OutrightCategory[].class);
        assertTrue(outrightCategoryList.length > 0, "List of outright for categories is empty");
    }

    @Test(description = "get available outright for categories (RU)")
    public void getOutrightForCategoryRU() {
        //for russian (ru)
        outrightCategoryList = outrightAdapter.getOutright("27", "ru", "category", OutrightCategory[].class);
        assertTrue(outrightCategoryList.length > 0, "List of outright for categories is empty");
    }

    @DataProvider(name = "Params")
    public Object[][] params() {
        return new Object[][]{
                //outrights for competitors
                //langIso=null
                {"competitors", "1", null, 400, "Required String parameter 'langIso' is not present"},
                //outrightId=invalid
                {"competitors", "qwerty", "en", 400, "Type mismatch."},
                //outrightId=null
                {"competitors", "", "en", 400, "Type mismatch."},
                //outrightId=null & langIso=null
                {"competitors", "", null, 400, "Required String parameter 'langIso' is not present"},
                //outrightId=unknown
                {"competitors", "12345", "en", 200, ""},

                //outrights for tournament
                //langIso=null
                {"tournament", "25", null, 400, "Required String parameter 'langIso' is not present"},
                //outrightId=invalid
                {"tournament", "qwerty", "en", 400, "Type mismatch."},
                //outrightId=null
                {"tournament", "", "en", 400, "Type mismatch."},
                //outrightId=null & langIso=null
                {"tournament", "", null, 400, "Required String parameter 'langIso' is not present"},
                //outrightId=unknown
                {"tournament", "12345", "en", 200, ""},

                //outrights for category
                //langIso=null
                {"category", "27", null, 400, "Required String parameter 'langIso' is not present"},
                //outrightId=invalid
                {"category", "qwerty", "en", 400, "Type mismatch."},
                //outrightId=null
                {"category", "", "en", 400, "Type mismatch."},
                //outrightId=null & langIso=null
                {"category", "", null, 400, "Required String parameter 'langIso' is not present"},
                //outrightId=unknown
                {"category", "12345", "en", 200, ""},

                //outright by id
                //langIso=null
                {"", "103718", null, 400, "Required String parameter 'langIso' is not present"}
        };
    }

    @Test(description = "validate params", dataProvider = "Params")
    public void validateParams(String path, String id, String langIso, int expectedStatusCode, String responseMessage) {

        responseBetOn = outrightAdapter.validateParams(path, id, langIso, expectedStatusCode, responseMessage);
        if (StringUtils.isNotEmpty(responseMessage)) {
            assertEquals(responseBetOn.getMessage(), responseMessage, "Invalid response");
        }
    }

    @Test(description = "put list of outright by bid")
    public void putOutrightByBid() {
        int[] bids = {92973, 103718};
        outrightAdapter.putOutrightByBid(bids);
    }

    @Test(description = "put list of outright by bid (invalid values)")
    public void putOutrightByInvalidBid() {
        //invalid bid
        outrightAdapter.putOutrightByInvalidBid("[99999999]");
        //empty bid
        outrightAdapter.putOutrightByInvalidBid("[]");
    }

    @Test(description = "get outright by bid")
    public void getOutrightByBid() {
        String bid = "103718";
        OutrightCategory outright = outrightAdapter.getOutright(bid, "en", "", OutrightCategory.class);
        assertEquals(String.valueOf(outright.getBid()), bid, "Invalid bid");
    }

    @Test(description = "get outright by bid with invalid langIso")
    public void getOutrightByBidInvalidLangIso() {
        String bid = "103718";
        OutrightCategory outright = outrightAdapter.getOutright(bid, "qwerty", "", OutrightCategory.class);
        assertEquals(String.valueOf(outright.getBid()), bid, "Invalid bid");
    }

    @Test(description = "get outright by bid with invalid bid")
    public void getOutrightByBidInvalidBid() {
        String bid = "345676543";
        outrightAdapter.getOutright(bid, "en");
    }
}
