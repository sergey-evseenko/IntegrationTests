package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Bet {
    @SerializedName("bmi")
    @Expose
    int betRadarId;
    @SerializedName("ei")
    @Expose
    String eventId;
    @SerializedName("lc")
    @Expose
    String language;
    @SerializedName("mi")
    @Expose
    String marketId;
    @SerializedName("oi")
    @Expose
    int outcomeId;
}
