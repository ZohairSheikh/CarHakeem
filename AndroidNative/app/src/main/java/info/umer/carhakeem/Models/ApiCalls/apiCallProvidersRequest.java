package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class apiCallProvidersRequest {

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("txt")
    @Expose
    private String txt;


    public apiCallProvidersRequest(int limit, int page,String txt) {
        this.limit = limit;
        this.page = page;
        this.txt = txt;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
