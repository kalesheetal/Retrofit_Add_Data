package com.sk.rtrofit_adddataintodatabase;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @POST("Retrofit_AddData.php")
    Call<PojoClass> checkDatabaseConnection(@QueryMap Map<String , String > map);
}
