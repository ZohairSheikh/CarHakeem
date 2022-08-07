package info.umer.carhakeem.Models.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import info.umer.carhakeem.Models.ApiCalls.internals.Provider;

public class apiCallProvidersResponse {

    @SerializedName("providers")
    @Expose
    private List<Provider> providers = null;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("currentPage")
    @Expose
    private String currentPage;

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
