package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import info.umer.carhakeem.Models.ApiCalls.internals.apiCallMyAppointmentResponseOfReponse;

public class apiCallMyAppointmentResponse {
    @SerializedName("response")
    @Expose
    private List<apiCallMyAppointmentResponseOfReponse> response = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public List<apiCallMyAppointmentResponseOfReponse> getResponse() {
        return response;
    }

    public void setResponse(List<apiCallMyAppointmentResponseOfReponse> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }


}
