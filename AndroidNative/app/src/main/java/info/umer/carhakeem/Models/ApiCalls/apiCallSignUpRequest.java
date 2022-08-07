package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallSignUpRequest {
    public apiCallSignUpRequest(String mobileNo, String emailAddress, String name, String password, String userType) {
        this.mobileNo = mobileNo;
        this.emailAddress = emailAddress;
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("userType")
    @Expose
    private String userType;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
