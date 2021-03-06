package adapters;

import io.restassured.http.ContentType;
import models.ResponseBetOn;
import models.ValidateField;

import static io.restassured.RestAssured.given;

public class FieldValidationAdapter extends MainAdapter {
    String url = "auth/v1/users/field/validate";

    public ResponseBetOn[] post(ValidateField validateField) {
        body = gson.toJson(validateField);
        requestSpec = given()
                .contentType(ContentType.JSON)
                .body(body);
        response = post(url, requestSpec, 200);
        return gson.fromJson(response.asString().trim(), ResponseBetOn[].class);
    }
}
