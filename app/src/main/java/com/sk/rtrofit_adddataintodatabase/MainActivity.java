package com.sk.rtrofit_adddataintodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String WEB_URL = "https://androidbatch2.000webhostapp.com/";
    TextView txtsuccess;
    EditText edtname,edtemail,edtphone,edtaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtsuccess = findViewById(R.id.textsuccess);

        edtname = findViewById(R.id.editname);
        edtemail = findViewById(R.id.editemail);
        edtphone = findViewById(R.id.editphone);
        edtaddress = findViewById(R.id.editaddress);
    }

    public void addData(View view) {
        Retrofit retrofit=null;

        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(WEB_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        ApiInterface apiInterface;

        apiInterface = retrofit.create(ApiInterface.class);

        Map<String,String> param = new HashMap<>();
        param.put("name",edtname.getText().toString());
        param.put("email",edtemail.getText().toString());
        param.put("phone",edtphone.getText().toString());
        param.put("address",edtaddress.getText().toString());

        Call<PojoClass> call = apiInterface.checkDatabaseConnection(param);

        call.enqueue(new Callback<PojoClass>() {
            @Override
            public void onResponse(Call<PojoClass> call, Response<PojoClass> response) {
                txtsuccess.setText(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<PojoClass> call, Throwable t) {
                txtsuccess.setText(t.getMessage());
            }
        });

    }
}