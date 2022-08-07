package info.umer.carhakeem.Helpers.Entities;


public class Constants {

    public static boolean encryptedCommunication = true;



    public enum Environment {

        Dev,

    }

    // Dev for local Development



    public static Environment env = Environment.Dev;

    public static String apiCallLogin = "apiCallLogin";
    public static String apiCallCreateProvider = "apiCallCreateProvider";
    public static String apiCallSignUp = "apiCallSignUp";
    public static String apiCallProviders = "apiCallProviders";
    public static String apiCallProviderType = "apiCallProviderType";
    public static String apiCallMakeAppointment = "apiCallMakeAppointment";
    public static String apiCallMyAppointment = "apiCallMyAppointment";
    public static String apiCallMyServices = "apiCallMyServices";
    public static String apiCallAppointmentStatus = "apiCallAppointmentStatus";
    public static String userLoginTime = "userLoginTime";
    public static String userType = "userType";

    public static String apiError = "apiError";

    public static String userAccessToken = "userAccessToken";
    public static String sessionsId = "sessionsId";
    public static String userName = "userName";
    public static String userMobileNo = "userMobileNo";
    public static String userEmail = "userEmail";
    public static String userLoggedIn = "userLoggedIn";
    public static String userId = "userId";

    /////////////////////////////////


    public static String getMainUrl() {
        if (env == Environment.Dev) {
            return "https://laptopdetect.herokuapp.com/";  }

        return "";
    }


 
}
