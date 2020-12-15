package com.example.myretrofiapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myretrofiapp.Retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText edtEmail,edtName,edtPassword,edtPhone;
    Button register;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    String baseUrl = "http://192.168.2.18:3000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = (EditText) findViewById(R.id.editxtreg_email);
        edtName = (EditText) findViewById(R.id.editxtreg_name);
        edtPassword = (EditText) findViewById(R.id.editxtreg_password);
        edtPhone = (EditText) findViewById(R.id.editxtreg_phone);
        register = (Button) findViewById(R.id.btn_register2);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    public void registerUser(View v){
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        Call<String> call = retrofitInterface.Signup(name,phone,email,password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.body().contains("Success")){

                    Toast.makeText(Register.this, "Successfully Signup", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Register.this, "Failed !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Register.this, t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
