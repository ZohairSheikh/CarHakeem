package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallAppointmentStatusRequest {
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
