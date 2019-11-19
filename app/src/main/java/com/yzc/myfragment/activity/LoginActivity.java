package com.yzc.myfragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzc.myfragment.R;
import com.yzc.myfragment.bean.LoginResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_pwd)EditText et_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.bt_register)
    void Register(){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

    }
    @OnClick(R.id.bt_login)
    void login(){

        final String username=et_username.getText().toString();
        final String pwd=et_pwd.getText().toString();

        String url="http://10.10.16.23:8088/MobileShop/member/login2";


        /*new Thread(){
            @Override
            public void run() {
                super.run();*/
        OkHttpUtils
                .post()
                .url(url)
                .addParams("input",username)
                .addParams("password",pwd)
                .build()
                .execute(new StringCallback(){
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //失败
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        LoginResponse response1 = gson.fromJson(response, LoginResponse.class);
                        if (response1.getStatus()==0){
                            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            SpTools.setBoolean("isLogni",true);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,response1.getStatus(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
      /*  OkHttpClient httpClient=new OkHttpClient.Builder().build();
        FormBody body=new FormBody.Builder()
                .add("input",username)
                .add("password",pwd)
                .build();
        Request request = new Request.Builder().url(url).post(body).build();*/
               /* OkHttpClient httpClient=new OkHttpClient();
                Request request = new Request
                        .Builder()
                        .url("10.10.16.65:8089/MobileShop/")
                        .get()
                        .build();*/

       /* httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                final LoginResponse loginResponse = gson.fromJson(json, LoginResponse.class);

               if (loginResponse.getStatus()==0){

                   SpTools.setBoolean("islogin",true);
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                       }
                   });
                   finish();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,loginResponse.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });*/
               /* try {
                    Response response=httpClient.newCall(request).execute();
                    Log.d("Response",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
}
