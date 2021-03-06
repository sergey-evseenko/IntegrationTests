package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Expose
    String city;
    @Expose
    String countryCode;
    @Expose
    String homeNumber;
    @Expose
    String postalCode;
    @Expose
    String street;
}
