package info.umer.carhakeem.Helpers.Http;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.Toast;

import com.google.gson.Gson;

import info.umer.carhakeem.Helpers.Entities.App;
import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.Entities.Utils;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IBase;
import info.umer.carhakeem.Interfaces.apiDefinition;
import info.umer.carhakeem.Models.ApiCalls.apiCallAppointmentStatusRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallCreateProviderRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallErrorResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallLoginRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMakeAppointmentRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyAppointmentsRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyServicesRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallProvidersRequest;
import info.umer.carhakeem.Models.ApiCalls.apiCallSignUpRequest;
import info.umer.carhakeem.UI.Custom.Loading_Dialog;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class callApi {
    public IBase ibase;
    ProgressDialog loadingDialog;
    Context context;

    public callApi(IBase _ibase, Context _context) {
        ibase = _ibase;
        context = _context;


    }

    protected void showLoadingDialog(String message) {
        loadingDialog = Loading_Dialog.ctor(context);
        loadingDialog.setMessage("");
        loadingDialog.show();
    }


    protected void hideLoadingDialog() {

        if (loadingDialog != null)
            loadingDialog.dismiss();

    }

    public void apiCal(final String type, Object cv, final boolean loader) {


        if (loader) {
            showLoadingDialog("");
        }
        apiDefinition apiService;
        RequestBody body = null;
        if (cv != null) {
            try {
                App.get()._logs.writeToFile("\nonApiRequest ***************************************** Time: " + Utils.getCurrentDateTime(1));
                App.get()._logs.writeToFile("onApiRequest isEncrypted: " + Constants.encryptedCommunication + " ");
                App.get()._logs.writeToFile("onApiResponseType: " + type);
                App.get()._logs.writeToFile("onApiRequestToken: " + sharedPrefs.getString(Constants.userAccessToken) + " ");
                App.get()._logs.writeToFile("onApiRequestSessionId: " + sharedPrefs.getString(Constants.sessionsId) + " ");
                App.get()._logs.writeToFile("onApiRequestBody: " + new Gson().toJson(cv) + " ");
                App.get()._logs.writeToFile("onApiRequest: *****************************************");
            } catch (Exception e) {
            }
        }
        apiService = RestCall.getMainClient(false).create(apiDefinition.class);
        Call<ResponseBody> call = null;

        if (Constants.apiCallLogin.equals(type)) {
            apiCallLoginRequest cls = (apiCallLoginRequest) cv;
            call = apiService.login(cls);
        }
      else  if (Constants.apiCallSignUp.equals(type)) {
            apiCallSignUpRequest cls = (apiCallSignUpRequest) cv;
            call = apiService.signup(cls);
        }

        else  if (Constants.apiCallProviders.equals(type)) {
            apiCallProvidersRequest cls = (apiCallProvidersRequest) cv;
            call = apiService.providers(cls.getPage(),30,cls.getTxt(),App.get()._mapHelper.ltlng.latitude,App.get()._mapHelper.ltlng.longitude);
        }
        else  if (Constants.apiCallProviderType.equals(type)) {
           // apiCallProvidersRequest cls = (apiCallProvidersRequest) cv;
            call = apiService.providerTypes();
        }

        else  if (Constants.apiCallMakeAppointment.equals(type)) {
            apiCallMakeAppointmentRequest cls = (apiCallMakeAppointmentRequest) cv;
            call = apiService.makeAppointment(cls);
        }

        else  if (Constants.apiCallMyAppointment.equals(type)) {
            apiCallMyAppointmentsRequest cls = (apiCallMyAppointmentsRequest) cv;
            call = apiService.myAppointments(cls);
        }
        else  if (Constants.apiCallMyServices.equals(type)) {
            apiCallMyServicesRequest cls = (apiCallMyServicesRequest) cv;
            call = apiService.myServices(cls);
        }

        else  if (Constants.apiCallAppointmentStatus.equals(type)) {
            apiCallAppointmentStatusRequest cls = (apiCallAppointmentStatusRequest) cv;
            call = apiService.appointmentStatus(cls);
        }
        else  if (Constants.apiCallCreateProvider.equals(type)) {
            apiCallCreateProviderRequest cls = (apiCallCreateProviderRequest) cv;
            call = apiService.addProvider(cls);
        }


        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (loader) {
                    hideLoadingDialog();
                }

                try {
                    int responceCode = response.code();
                    String responceMessagae = response.message();
                    String responceBody = "";

                    if (response.isSuccessful()) {

                        responceBody = response.body().string();

                    } else {
                        responceBody = response.errorBody().string();
                    }


                    App.get()._logs.writeToFile("\nonApiResponse ***************************************** Time: " + Utils.getCurrentDateTime(1));
                    App.get()._logs.writeToFile("onApiResponseType " + type);
                    App.get()._logs.writeToFile("onApiResponseBody Encrypted " + responceBody);


                    App.get()._logs.writeToFile("onApiResponseBody Decrypted " + responceBody);
                    App.get()._logs.writeToFile("onApiResponseCode " + responceCode);
                    App.get()._logs.writeToFile("onApiResponse ***************************************** End ");

                    if(responceCode == 200)
                    {

                        Object obj;
                        obj = new Gson().fromJson(responceBody, Utils.getResponseClass(type));
                        if (ibase != null) {
                            ibase.apiCallBack(obj, type);
                        }
                    }
                    else
                    {
                        Object obj;
                        obj = new Gson().fromJson(responceBody, Utils.getResponseClass(Constants.apiError));

                        if (ibase != null) {
                            ibase.apiCallBackFailed(obj, type);
                        }
                        showToast(((apiCallErrorResponse) obj).getMessage());
                    }


                } catch (Exception e) {

                    showToast(" " + e.getMessage());
                    //  Log.i("CallApiException", "Type : " + type + " " + e.getMessage());


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (loader) {
                    hideLoadingDialog();
                }

                //  Log.d("onApiFailure", "***************************************** Time " + Utils.getCurrentDateTime(1));
                try {
                    //   Log.d("onApiFailureMsg", t.getCause().getMessage() + " ");
                } catch (Exception e) {
                    try {
                        //    Log.d("onApiFailureMsgExcep", t.getMessage() + " ");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                //  Log.d("onApiResponse", "*****************************************");


                App.get()._logs.writeToFile("\nonApiResponse ***************************************** Time: " + Utils.getCurrentDateTime(1));
                try {

                    App.get()._logs.writeToFile("onApiFailureMsg " + t.getCause().getMessage());
                } catch (Exception e) {
                    try {
                        App.get()._logs.writeToFile("onApiFailureMsgExcep " + t.getMessage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                App.get()._logs.writeToFile("onApiResponse ***************************************** End ");


                try {
                    String msg = t.getCause().getMessage();
                    if (msg.toLowerCase().contains("socket close")) {
                        showToast("Network Error : Request Timeout");
                    } else if (msg.toLowerCase().contains("failed to connect")) {
                        showToast("Network Failure");
                    } else {
                        showToast("Network Error : " + msg);
                    }

                } catch (Exception e) {

                    try {
                        String msg = t.getMessage();
                        if (msg.toLowerCase().contains("socket close")) {
                            showToast("Network Error : Request Timeout");
                        } else {
                            showToast("Network Error : " + msg);
                        }

                    } catch (Exception ex) {
                        //  ex.printStackTrace();
                    }
                    //  e.printStackTrace();
                }


            }
        });

    }

    protected void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    protected void showAlertDialog(String message_) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(message_);

        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

/*        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
*/

        AlertDialog alert11 = builder.create();
        alert11.show();

    }
}
