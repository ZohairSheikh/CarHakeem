package info.umer.carhakeem.Models.ApiCalls.internals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Provider {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("avg")
    @Expose
    private Double avg;
    @SerializedName("providers")
    @Expose
    private List<Providers> providers = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public List<Providers> getProviders() {
        return providers;
    }

    public void setProviders(List<Providers> providers) {
        this.providers = providers;
    }
}