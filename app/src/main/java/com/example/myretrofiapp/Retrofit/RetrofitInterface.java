package com.example.myretrofiapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/login")
    Call<String> Login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/register")
    Call<String> Signup(@Field("name") String name, @Field("phone") String phone  ,@Field("email") String email, @Field("password") String password);
}
