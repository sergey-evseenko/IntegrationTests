package tests.rulesEngine;

import adapters.RulesAdapter;
import models.Address;
import models.UserForCheck;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class CreateUserCheckTest extends BaseTest {

    UserForCheck userForCheck;
    Address address;

    @BeforeMethod(description = "get user for check")
    public void getUserForCheck() {
        userForCheck = data.get("userForCheck.json", UserForCheck.class);
    }

    @Test(description = "check not uniq email")
    public void checkNotUniqEmail() {
        userForCheck.setEmail("qwerty81@gmail.com");

        response = new RulesAdapter().checkUserPost(userForCheck);
        assertEquals(response.getEmail(), "E0005", "Not uniq email error");
    }

    @Test(description = "check not uniq user name")
    public void checkNotUniqUserName() {
        userForCheck.setUsername("qwerty81");

        response = new RulesAdapter().checkUserPost(userForCheck);
        assertEquals(response.getUserName(), "E0005", "Not uniq user name error");
    }

    @Test(description = "check not uniq FN, LN, nationality, date of birth")
    public void checkNotUniqParams() {
        userForCheck.setName("Test");
        userForCheck.setSurname("User");
        userForCheck.setNationalityId(19);
        userForCheck.setBirthDate("1990-02-05");

        response = new RulesAdapter().checkUserPost(userForCheck);
        assertEquals(response.getPlace(), "E0006", "Not uniq place error");
    }

    @Test(description = "check invalid age 18")
    public void checkInvalidAge18() {
        address = userForCheck.getAddress();
        address.setCountryCode("BY");
        userForCheck.setAddress(address);
        userForCheck.setBirthDate("2005-12-28");

        response = new RulesAdapter().checkUserPost(userForCheck);
        assertEquals(response.getAge(), "E0002", "Invalid BirthDate error");
    }

    @Test(description = "check invalid age 21")
    public void checkInvalidAge21() {
        address = userForCheck.getAddress();
        address.setCountryCode("EE");
        userForCheck.setAddress(address);
        userForCheck.setBirthDate("2001-02-05");

        response = new RulesAdapter().checkUserPost(userForCheck);
        assertEquals(response.getAge(), "E0002", "Invalid BirthDate error");
    }

    @Test(description = "check invalid passport number")
    public void checkInvalidPasswordNumber() {
        userForCheck.setPassportNumber("ASD84676");

        response = new RulesAdapter().checkUserPost(userForCheck);
        assertEquals(response.getIdCard(), "E0007", "Invalid BirthDate error");
    }
}