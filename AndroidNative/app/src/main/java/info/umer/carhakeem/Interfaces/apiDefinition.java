package info.umer.carhakeem.Interfaces;

import info.umer.carhakeem.Models.ApiCalls.apiCallAppointmentStatusRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallCreateProviderRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallLoginRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMakeAppointmentRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyAppointmentsRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyServicesRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallSignUpRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface apiDefinition {


    @POST("signin")
    Call<ResponseBody> login(@Body apiCallLoginRequest _req);


    @POST("signup")
    Call<ResponseBody> signup(@Body apiCallSignUpRequest _req);

    @GET("providers2")
    Call<ResponseBody> providers(@Query("page") int page, @Query("limit") int limit, @Query("txt") String txt, @Query("lat") double lat, @Query("lon") double lon);

    @GET("providerTypes")
    Call<ResponseBody> providerTypes();

    @POST("makeAppointment")
    Call<ResponseBody> makeAppointment(@Body apiCallMakeAppointmentRequest _req);

    @POST("myAppointments")
    Call<ResponseBody> myAppointments(@Body apiCallMyAppointmentsRequest _req);


    @POST("myServices")
    Call<ResponseBody> myServices(@Body apiCallMyServicesRequest _req);

    @POST("appointmentStatus")
    Call<ResponseBody> appointmentStatus(@Body apiCallAppointmentStatusRequest _req);


    @POST("addProvider")
    Call<ResponseBody> addProvider(@Body apiCallCreateProviderRequest _req);


}
