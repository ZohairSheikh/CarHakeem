package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallMyAppointmentsRequest {

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("userType")
    @Expose
    private String userType;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
