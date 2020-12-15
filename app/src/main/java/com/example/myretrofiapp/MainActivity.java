package com.example.myretrofiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    EditText txtemail,txtpassword;
    Button btn_login,btn_reg;

    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    String baseUrl = "http://192.168.2.18:3000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtemail = (EditText) findViewById(R.id.editxt_email);
        txtpassword = (EditText) findViewById(R.id.editxt_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_reg = (Button)findViewById(R.id.btn_register);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    public  void loginUser(View v)
    {
        String email = txtemail.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        Call<String> call = retrofitInterface.Login(email,password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body().contains("Success")) {
                    Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,detail.class));
                } else {

                    Toast.makeText(MainActivity.this, "Failed ! check your username and password ", Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public  void registerUser(View v)
    {
        startActivity(new Intent(MainActivity.this, Register.class));
    }

}