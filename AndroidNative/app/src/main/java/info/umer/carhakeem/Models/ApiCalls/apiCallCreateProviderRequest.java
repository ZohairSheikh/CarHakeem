package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallCreateProviderRequest {
    @SerializedName("providerName")
    @Expose
    private String providerName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }


    public apiCallCreateProviderRequest(String providerName, String address, String category, String contactNo, String userId, String district, String area, Double lat, Double lon, Integer rate) {
        this.providerName = providerName;
        this.address = address;
        this.category = category;
        this.contactNo = contactNo;
        this.userId = userId;
        this.district = district;
        this.area = area;
        this.lat = lat;
        this.lon = lon;
        this.rate = rate;
    }
}
