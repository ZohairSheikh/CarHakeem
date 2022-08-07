package info.umer.carhakeem.Helpers.Http;


import java.util.concurrent.TimeUnit;

import info.umer.carhakeem.Helpers.Entities.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCall {

    private static Retrofit retrofitMain = null;
    private static Retrofit retrofitMainForFile = null;


    public static Retrofit getMainClient(boolean isFile) {

        if (retrofitMain==null) {



            final OkHttpClient okHttpClient = new OkHttpClient.Builder()

                    .readTimeout(150, TimeUnit.SECONDS)
                    .connectTimeout(150, TimeUnit.SECONDS)
                    .callTimeout(150, TimeUnit.SECONDS)
//                    .addInterceptor(new Interceptor() {
//                        @NotNull
//                        @Override
//                        public Response intercept(@NotNull Chain chain) throws IOException {
//                            Request request = chain.request().newBuilder().addHeader("Authorization", "Basic aVRhbGtQRkNsaWVudDoxODA3YmU1Mi03MGFlLTQ1ZmYtYTdlMi1iMjNiM2IzZGZiMzM=").build();
//                            return chain.proceed(request);
//                        }
//                    })
                    .build();

            retrofitMain = new Retrofit.Builder()
                    .baseUrl(!isFile? Constants.getMainUrl() : Constants.getMainUrl().replace("api/",""))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        if(retrofitMainForFile==null && isFile)
        {


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()

                    .readTimeout(150, TimeUnit.SECONDS)
                    .connectTimeout(150, TimeUnit.SECONDS)
                    .callTimeout(150, TimeUnit.SECONDS)
//                    .addInterceptor(new Interceptor() {
//                        @NotNull
//                        @Override
//                        public Response intercept(@NotNull Chain chain) throws IOException {
//                            Request request = chain.request().newBuilder().addHeader("deviceName", App.get().deviceType).build();
//                            return chain.proceed(request);
//                        }
//                    })

                    .build();

            retrofitMainForFile = new Retrofit.Builder()
                    .baseUrl(!isFile? Constants.getMainUrl() : Constants.getMainUrl().replace("api/",""))
                    .addConverterFactory(ScalarsConverterFactory.create())

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return !isFile? retrofitMain: retrofitMainForFile;
    }




}
